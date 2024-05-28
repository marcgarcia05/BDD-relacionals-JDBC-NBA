# 📦 Practica BDD relacionals amb JDBC (NBA) 🛒

Pràctica 6.1 1rDAW M03 - Alberto González - Marc Garcia

---

## Descripció

Aquest projecte implementa un sistema de gestió de bases de dades relacionals per a estadístiques de la NBA utilitzant Java i JDBC. Permet als usuaris gestionar i consultar dades relacionades amb equips, jugadors i partits de la NBA.

## Importació de Dades a la BBDD

Les dades s'importen a la base de dades a través d'endpoints que utilitzen la llibreria `nba_api` per recuperar informació actualitzada. Aquest procés es realitza en Python, creant i actualitzant taules a la base de dades amb la informació obtinguda.

## Taules i Dades a la BBDD

* players: Informació detallada dels jugadors com el nom, ID, equip, etc.
* team_info: Informació dels equips com el nom, ciutat, conferència, etc.
* team_game_logs: Detalls dels partits jugats incloent data, equips participants i resultat. Des de l'any 2018-19.
* player_info: Estadístiques individuals de cada jugador per partit.
* historics: Taula destinada als jugadors que es volguin retirar.

## Justificacions 📋

## Apartats del codi:

El projecte està organitzat en diverses capes:

* Model: És responsable de gestionar les dades de l'aplicació.
* Vista: La vista és responsable de la presentació de les dades a l'usuari. `PlayerView`, `TeamView`, `MatchView` i `MainView` contenen mètodes per a rebre entrades de l'usuari.
* Controlador: Responsable de gestionar la lògica de l'aplicació. `PlayerController`, `TeamController` i `MatchController` gestionen les operacions sobre jugadors, equips i partits respectivament.
* DAO (Data Access Object): Gestiona la comunicació amb la base de dades. `PlayerDAO`, `TeamDAO` i `MatchDAO` contenen mètodes per a executar consultes i actualitzacions a la base de dades.




# 💥 Maneig d'Errors 🚨

En el cas que es produeixi un error durant l'execució del codi, hem implementat diverses estratègies per gestionar-los de manera adequada.

#### Informació i Gestió d'Errors

En cas d'error, el programa proporciona un missatge d'error descriptiu que informa a l'usuari sobre la naturalesa del problema. Això ajuda a l'usuari a identificar i comprendre el problema més fàcilment, millorant la transparència i l'usabilitat del programa.

```sh
System.out.print("Introdueix el preu del producte: ");
            try {
                preu = scanner.nextFloat();
                preuValid = true;
            } catch (Exception e) {
                System.out.println("Introdueix un valor numèric vàlid per al preu del producte.");
                scanner.nextLine();
                registrarErrorEnLogs(new Exception("Preu del producte alimentació incorrecte."));
            }
        }
```

#### Recuperació i Continuïtat

En molts casos, el programa intenta recuperar-se de l'error i continuar amb l'execució del codi de manera adequada. Per exemple, en el cas de l'entrada de dades incorrectes, el programa pot demanar a l'usuari que introdueixi de nou les dades correctes.

```sh
Introdueix el nom del producte: Poma
Introdueix el preu del producte: ASD
Introdueix un valor numèric vàlid per al preu del producte.
Introdueix el preu del producte:
```

#### Registre d'Errors

També he implementat una funcionalitat per enregistrar els errors en un fitxer de registre dedicat. Això ens permet tenir un seguiment dels errors que es produeixen durant l'execució del programa i facilita la seva resolució posterior. El registre d'errors inclou informació detallada sobre l'excepció, com el missatge d'error i la traça de pila associada. Això és el que veuríem.

```sh
-------------------------------------------
Excepció: Preu del producte alimentació incorrecte.
java.lang.Exception: Preu del producte alimentació incorrecte.
	at Supermercat.afegirProducteAlimentacio(Supermercat.java:231)
	at Supermercat.menuAfegirProducte(Supermercat.java:194)
	at Supermercat.main(Supermercat.java:143)
-------------------------------------------
```
