import pandas as pd
from nba_api.stats.endpoints import TeamInfoCommon
from nba_api.stats.static import teams
import time
import mysql.connector
from mysql.connector import errorcode

# MySQL database credentials
db_config = {
    'host': '192.168.1.102',
    'user': 'root',
    'password': 'casaos',
    'database': 'nba'
}

# Conexión a la base de datos
try:
    conn = mysql.connector.connect(**db_config)
    cursor = conn.cursor()

    # Crear tabla si no existe
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS team_info (
        TEAM_ID VARCHAR(255),
        SEASON_YEAR VARCHAR(255),
        TEAM_CITY VARCHAR(255),
        TEAM_NAME VARCHAR(255),
        TEAM_ABBREVIATION VARCHAR(255),
        TEAM_CONFERENCE VARCHAR(255),
        TEAM_DIVISION VARCHAR(255),
        TEAM_CODE VARCHAR(255),
        TEAM_SLUG VARCHAR(255),
        W VARCHAR(255),
        L VARCHAR(255),
        PCT VARCHAR(255),
        CONF_RANK VARCHAR(255),
        DIV_RANK VARCHAR(255),
        MIN_YEAR VARCHAR(255),
        MAX_YEAR VARCHAR(255),
        TEAMNAME VARCHAR(255),
        SEASON VARCHAR(255)
    )
    """)
    conn.commit()

    # Get the list of NBA teams
    nba_teams = teams.get_teams()

    # Create a dictionary of team IDs
    team_ids_dict = {team['full_name']: team['id'] for team in nba_teams}

    # Create an empty DataFrame to store the data
    df = pd.DataFrame()

    # Loop through the teams and append to the df
    for team_name, team_id in team_ids_dict.items():
        try:
            team_info = TeamInfoCommon(team_id=team_id)
            df_team = team_info.get_data_frames()[0]
            df_team['TeamName'] = team_name  # Adding the team name to the DataFrame
            df_team['Season'] = '2023-24'  # Adding the season to the DataFrame
            df = pd.concat([df, df_team], ignore_index=True)
            
            # Sleep to respect rate limits
            time.sleep(1)  # Adjust the sleep time if necessary
        except Exception as e:
            print(f"An error occurred for team {team_name}: {e}")

    # Print the combined DataFrame
    print("Primeras filas del DataFrame:")
    print(df.head())

    # Verificar las columnas presentes en el DataFrame
    print("Columnas del DataFrame:", df.columns)

    # Asegurarse de que las columnas requeridas existan
    required_columns = [
        'TEAM_ID', 'SEASON_YEAR', 'TEAM_CITY', 'TEAM_NAME', 'TEAM_ABBREVIATION', 
        'TEAM_CONFERENCE', 'TEAM_DIVISION', 'TEAM_CODE', 'TEAM_SLUG', 'W', 'L', 
        'PCT', 'CONF_RANK', 'DIV_RANK', 'MIN_YEAR', 'MAX_YEAR', 'TeamName', 'Season'
    ]

    for col in required_columns:
        if col not in df.columns:
            df[col] = None  # Añadir columnas faltantes con valores None

    # Insertar datos en la tabla team_info en bloques
    insert_query = """
    INSERT INTO team_info (
        TEAM_ID, SEASON_YEAR, TEAM_CITY, TEAM_NAME, TEAM_ABBREVIATION, TEAM_CONFERENCE, TEAM_DIVISION, TEAM_CODE, TEAM_SLUG, W, L, PCT, CONF_RANK, DIV_RANK, MIN_YEAR, MAX_YEAR, 
        TEAMNAME, SEASON
    ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
    """

    values = [
        (
            row['TEAM_ID'], row['SEASON_YEAR'], row['TEAM_CITY'], row['TEAM_NAME'], row['TEAM_ABBREVIATION'], row['TEAM_CONFERENCE'], row['TEAM_DIVISION'], 
            row['TEAM_CODE'], row['TEAM_SLUG'], row['W'], row['L'], row['PCT'], row['CONF_RANK'], row['DIV_RANK'], row['MIN_YEAR'], row['MAX_YEAR'],  
            row['TeamName'], row['Season']
        )
        for index, row in df.iterrows()
    ]

    cursor.executemany(insert_query, values)
    conn.commit()
    print("Datos insertados correctamente en la tabla team_info")

except mysql.connector.Error as err:
    if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
        print("Algo está mal con tu usuario o contraseña")
    elif err.errno == errorcode.ER_BAD_DB_ERROR:
        print("La base de datos no existe")
    else:
        print(err)
finally:
    if 'cursor' in locals():
        cursor.close()
    if 'conn' in locals():
        conn.close()
