import mysql.connector
from nba_api.stats.endpoints import TeamGameLogs

# Conexión a la base de datos
try:
    conn = mysql.connector.connect(
        host="192.168.1.102",
        user="root",
        password="casaos",
        database="nba"
    )
    cursor = conn.cursor()

    # Crear tabla si no existe
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS team_game_logs (
        SEASON_YEAR VARCHAR(255),
        TEAM_ID VARCHAR(255),
        TEAM_ABBREVIATION VARCHAR(255),
        TEAM_NAME VARCHAR(255), 
        GAME_ID VARCHAR(255), 
        GAME_DATE VARCHAR(255), 
        MATCHUP VARCHAR(255),
        WL VARCHAR(255), 
        MIN VARCHAR(255), 
        FGM VARCHAR(255), 
        FGA VARCHAR(255), 
        FG_PCT VARCHAR(255), 
        FG3M VARCHAR(255), 
        FG3A VARCHAR(255), 
        FG3_PCT VARCHAR(255), 
        FTM VARCHAR(255), 
        FTA VARCHAR(255), 
        FT_PCT VARCHAR(255), 
        OREB VARCHAR(255), 
        DREB VARCHAR(255), 
        REB VARCHAR(255),
        AST VARCHAR(255), 
        TOV VARCHAR(255), 
        STL VARCHAR(255), 
        BLK VARCHAR(255), 
        BLKA VARCHAR(255), 
        PF VARCHAR(255), 
        PFD VARCHAR(255), 
        PTS VARCHAR(255), 
        PLUS_MINUS VARCHAR(255), 
        GP_RANK VARCHAR(255), 
        W_RANK VARCHAR(255), 
        L_RANK VARCHAR(255), 
        W_PCT_RANK VARCHAR(255), 
        MIN_RANK VARCHAR(255), 
        FGM_RANK VARCHAR(255), 
        FGA_RANK VARCHAR(255), 
        FG_PCT_RANK VARCHAR(255), 
        FG3M_RANK VARCHAR(255), 
        FG3A_RANK VARCHAR(255), 
        FG3_PCT_RANK VARCHAR(255), 
        FTM_RANK VARCHAR(255), 
        FTA_RANK VARCHAR(255), 
        FT_PCT_RANK VARCHAR(255), 
        OREB_RANK VARCHAR(255), 
        DREB_RANK VARCHAR(255), 
        REB_RANK VARCHAR(255), 
        AST_RANK VARCHAR(255), 
        TOV_RANK VARCHAR(255), 
        STL_RANK VARCHAR(255), 
        BLK_RANK VARCHAR(255), 
        BLKA_RANK VARCHAR(255),
        PF_RANK VARCHAR(255),
        PFD_RANK VARCHAR(255),
        PTS_RANK VARCHAR(255),
        PLUS_MINUS_RANK VARCHAR(255)
    )
    """)
    conn.commit()

    # Recuperar datos de TeamGameLogs
    gamedatapull = TeamGameLogs(
        league_id_nullable='00',  # nba 00, g_league 20, wnba 10
        team_id_nullable='',  # can specify a specific team_id
        season_nullable='2023-24',
        season_type_nullable='Regular Season'  # Regular Season, Playoffs, Pre Season
    )
    
    df_season = gamedatapull.get_data_frames()[0]

    # Insertar datos en la tabla team_game_logs
    insert_query = """
    INSERT INTO team_game_logs (
        SEASON_YEAR, TEAM_ID, TEAM_ABBREVIATION, TEAM_NAME, GAME_ID, GAME_DATE, MATCHUP, WL, MIN, FGM, FGA, FG_PCT, 
        FG3M, FG3A, FG3_PCT, FTM, FTA, FT_PCT, OREB, DREB, REB, AST, TOV, STL, BLK, BLKA, PF, PFD, PTS, PLUS_MINUS, 
        GP_RANK, W_RANK, L_RANK, W_PCT_RANK, MIN_RANK, FGM_RANK, FGA_RANK, FG_PCT_RANK, FG3M_RANK, FG3A_RANK, 
        FG3_PCT_RANK, FTM_RANK, FTA_RANK, FT_PCT_RANK, OREB_RANK, DREB_RANK, REB_RANK, AST_RANK, TOV_RANK, 
        STL_RANK, BLK_RANK, BLKA_RANK, PF_RANK, PFD_RANK, PTS_RANK, PLUS_MINUS_RANK
    ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
    """

    for index, row in df_season.iterrows():
        values = (
            row['SEASON_YEAR'], row['TEAM_ID'], row['TEAM_ABBREVIATION'], row['TEAM_NAME'], row['GAME_ID'], row['GAME_DATE'], 
            row['MATCHUP'], row['WL'], row['MIN'], row['FGM'], row['FGA'], row['FG_PCT'], row['FG3M'], row['FG3A'], row['FG3_PCT'], 
            row['FTM'], row['FTA'], row['FT_PCT'], row['OREB'], row['DREB'], row['REB'], row['AST'], row['TOV'], row['STL'], 
            row['BLK'], row['BLKA'], row['PF'], row['PFD'], row['PTS'], row['PLUS_MINUS'], row['GP_RANK'], row['W_RANK'], 
            row['L_RANK'], row['W_PCT_RANK'], row['MIN_RANK'], row['FGM_RANK'], row['FGA_RANK'], row['FG_PCT_RANK'], 
            row['FG3M_RANK'], row['FG3A_RANK'], row['FG3_PCT_RANK'], row['FTM_RANK'], row['FTA_RANK'], row['FT_PCT_RANK'], 
            row['OREB_RANK'], row['DREB_RANK'], row['REB_RANK'], row['AST_RANK'], row['TOV_RANK'], row['STL_RANK'], 
            row['BLK_RANK'], row['BLKA_RANK'], row['PF_RANK'], row['PFD_RANK'], row['PTS_RANK'], row['PLUS_MINUS_RANK']
        )
        cursor.execute(insert_query, values)
        print("Fila insertada:", cursor.rowcount)

    conn.commit()

except mysql.connector.Error as err:
    print("Error al conectarse a la base de datos:", err)

finally:
    if 'cursor' in locals():
        cursor.close()
    if 'conn' in locals():
        conn.close()
