import mysql.connector
from nba_api.stats.endpoints import CommonAllPlayers
from nba_api.stats.library.parameters import LeagueID, Season

# Conexión a la base de datos
try:
    conn = mysql.connector.connect(
        host="192.168.1.102",
        user="root",
        password="casaos",
        database="nba"
    )
    cursor = conn.cursor()

    
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS players (
        PERSON_ID INT PRIMARY KEY,
        DISPLAY_LAST_COMMA_FIRST VARCHAR(255),
        DISPLAY_FIRST_LAST VARCHAR(255),
        ROSTERSTATUS INT,
        FROM_YEAR INT,
        TO_YEAR INT,
        PLAYERCODE VARCHAR(255),
        TEAM_ID INT,
        TEAM_CITY VARCHAR(255),
        TEAM_NAME VARCHAR(255),
        TEAM_ABBREVIATION VARCHAR(255),
        TEAM_CODE VARCHAR(255),
        GAMES_PLAYED_FLAG VARCHAR(255),
        OTHERLEAGUE_EXPERIENCE_CH VARCHAR(255)
    )
    """)
    conn.commit()

    
    common_all_players = CommonAllPlayers(
        is_only_current_season=1,
        league_id=LeagueID.default,
        season='2023-24'
    )
    df_common_players = common_all_players.get_data_frames()[0]

    
    for index, player_data in df_common_players.iterrows():
        values = (
            player_data['PERSON_ID'],
            player_data['DISPLAY_LAST_COMMA_FIRST'],
            player_data['DISPLAY_FIRST_LAST'],
            player_data['ROSTERSTATUS'],
            player_data['FROM_YEAR'],
            player_data['TO_YEAR'],
            player_data['PLAYERCODE'],
            player_data['TEAM_ID'],
            player_data['TEAM_CITY'],
            player_data['TEAM_NAME'],
            player_data['TEAM_ABBREVIATION'],
            player_data['TEAM_CODE'],
            player_data['GAMES_PLAYED_FLAG'],
            player_data['OTHERLEAGUE_EXPERIENCE_CH']
        )
        cursor.execute("""
        INSERT INTO players (
            PERSON_ID,
            DISPLAY_LAST_COMMA_FIRST,
            DISPLAY_FIRST_LAST,
            ROSTERSTATUS,
            FROM_YEAR,
            TO_YEAR,
            PLAYERCODE,
            TEAM_ID,
            TEAM_CITY,
            TEAM_NAME,
            TEAM_ABBREVIATION,
            TEAM_CODE,
            GAMES_PLAYED_FLAG,
            OTHERLEAGUE_EXPERIENCE_CH
        ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        ON DUPLICATE KEY UPDATE
            DISPLAY_LAST_COMMA_FIRST=VALUES(DISPLAY_LAST_COMMA_FIRST),
            DISPLAY_FIRST_LAST=VALUES(DISPLAY_FIRST_LAST),
            ROSTERSTATUS=VALUES(ROSTERSTATUS),
            FROM_YEAR=VALUES(FROM_YEAR),
            TO_YEAR=VALUES(TO_YEAR),
            PLAYERCODE=VALUES(PLAYERCODE),
            TEAM_ID=VALUES(TEAM_ID),
            TEAM_CITY=VALUES(TEAM_CITY),
            TEAM_NAME=VALUES(TEAM_NAME),
            TEAM_ABBREVIATION=VALUES(TEAM_ABBREVIATION),
            TEAM_CODE=VALUES(TEAM_CODE),
            GAMES_PLAYED_FLAG=VALUES(GAMES_PLAYED_FLAG),
            OTHERLEAGUE_EXPERIENCE_CH=VALUES(OTHERLEAGUE_EXPERIENCE_CH)
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
