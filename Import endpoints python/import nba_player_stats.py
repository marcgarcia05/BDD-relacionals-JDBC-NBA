import mysql.connector
from nba_api.stats.endpoints import LeagueLeaders

# Conexión a la base de datos
try:
    conn = mysql.connector.connect(
        host="192.168.1.102",
        user="root",
        password="casaos",
        database="nba"
    )
    cursor = conn.cursor()

    # Eliminar la tabla si ya existe
    cursor.execute("DROP TABLE IF EXISTS player_stats")
    conn.commit()

    # Crear la tabla con la columna TEAM_ID
    cursor.execute("""
    CREATE TABLE player_stats (
        PLAYER_ID INT PRIMARY KEY,
        RANK INT,
        PLAYER VARCHAR(255),
        TEAM_ID INT,
        TEAM VARCHAR(255),
        GP INT,
        MIN FLOAT,
        FGM FLOAT,
        FGA FLOAT,
        FG_PCT FLOAT,
        FG3M FLOAT,
        FG3A FLOAT,
        FG3_PCT FLOAT,
        FTM FLOAT,
        FTA FLOAT,
        FT_PCT FLOAT,
        OREB FLOAT,
        DREB FLOAT,
        REB FLOAT,
        AST FLOAT,
        STL FLOAT,
        BLK FLOAT,
        TOV FLOAT,
        PTS FLOAT,
        EFF FLOAT
    )
    """)
    conn.commit()

    # Obtener datos de la API
    league_leaders = LeagueLeaders(
        league_id='00',  # NBA
        season='2022-23',  # Cambia el año si es necesario
        per_mode48='Totals'  # "Totals", "Per48", "PerGame"
    )
    df_league_leaders = league_leaders.get_data_frames()[0]

    # Imprimir nombres de las columnas para verificar
    print(df_league_leaders.columns)

    # Insertar datos en la tabla
    for index, player_data in df_league_leaders.iterrows():
        values = (
            player_data['PLAYER_ID'],
            player_data['RANK'],
            player_data['PLAYER'],
            player_data['TEAM_ID'],
            player_data['TEAM'],
            player_data['GP'],
            player_data['MIN'],
            player_data['FGM'],
            player_data['FGA'],
            player_data['FG_PCT'],
            player_data['FG3M'],
            player_data['FG3A'],
            player_data['FG3_PCT'],
            player_data['FTM'],
            player_data['FTA'],
            player_data['FT_PCT'],
            player_data['OREB'],
            player_data['DREB'],
            player_data['REB'],
            player_data['AST'],
            player_data['STL'],
            player_data['BLK'],
            player_data['TOV'],
            player_data['PTS'],
            player_data['EFF']
        )
        cursor.execute("""
        INSERT INTO player_stats (
            PLAYER_ID, RANK, PLAYER, TEAM_ID, TEAM, GP, MIN, FGM, FGA, FG_PCT,
            FG3M, FG3A, FG3_PCT, FTM, FTA, FT_PCT, OREB, DREB, REB, AST, STL,
            BLK, TOV, PTS, EFF
        ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON DUPLICATE KEY UPDATE
            RANK=VALUES(RANK),
            PLAYER=VALUES(PLAYER),
            TEAM_ID=VALUES(TEAM_ID),
            TEAM=VALUES(TEAM),
            GP=VALUES(GP),
            MIN=VALUES(MIN),
            FGM=VALUES(FGM),
            FGA=VALUES(FGA),
            FG_PCT=VALUES(FG_PCT),
            FG3M=VALUES(FG3M),
            FG3A=VALUES(FG3A),
            FG3_PCT=VALUES(FG3_PCT),
            FTM=VALUES(FTM),
            FTA=VALUES(FTA),
            FT_PCT=VALUES(FT_PCT),
            OREB=VALUES(OREB),
            DREB=VALUES(DREB),
            REB=VALUES(REB),
            AST=VALUES(AST),
            STL=VALUES(STL),
            BLK=VALUES(BLK),
            TOV=VALUES(TOV),
            PTS=VALUES(PTS),
            EFF=VALUES(EFF)
        """, values)
        print("Fila insertada:", cursor.rowcount)

    conn.commit()

except mysql.connector.Error as err:
    print("Error al conectarse a la base de datos:", err)

finally:
    if 'cursor' in locals():
        cursor.close()
    if 'conn' in locals():
        conn.close()
