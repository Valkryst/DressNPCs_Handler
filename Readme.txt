------------------ English --------------------------------------------------------------------------------------------

Requirements:
	Java 8



General:
	If a component is surrounded by a blue outline, then it's currently selected.

	If a component is surrounded by a red outline, then there's an error with that field. In some cases,
	such as when there's an error in the login information, a number of fields will be shown as having 
	an error. In these cases that's just because there's not much of any information on which field caused
	the error.
	When there is an error, you'll find a (possibly) helpful description of the problem after mousing over
	the field and allowing the tooltip to appear.

	Almost every component has a tooltip, so I suggest reading them if you need more information on a field.



Login Screen:
	Enter all of the required information and then hit Submit. You will be brought to the main program
	if a database connection was established.

	If you click Save, then all of your information other than your password will be saved. When you
	next launch the program, your password field should be highlighted in red (don't worry if it isn't)
	and you'll just need to enter it before hitting Submit.



DressNPC Handler Screen:
	Whatever information you don't set will be set as 0 when you click Submit, so you can leave fields
	blank.

	Enter the information for your NPC, then click Submit to send it to the database.

	If there's already a DressNPC in the database using the Model Entry ID that you're using, then, when you hit Submit, all of it's data will be overwritten by your new data.



Creature Weapon Handler Screen:
	Although there are four sections for equipment sets on this screen, you can use one, two, three, or four
	of them. It doesn't matter. If you do leave any of them blank, when you click Submit, their NPC Entry ID
	and Equipment Set ID fields will be shown as having an error. This doesn't mean anything, it's just how
	the program currently works, so you can ignore those errors.

	Each NPC in the creature_template table can have any number of Equipment Sets. A set is randomly chosen
	every time the creature is spawned. The NPC Entry ID corresponds to the entry ID in the creature_template
	table.

	The Equipment Set ID's begin at 1.

	Due to program complexity, I had the choice to either allow the user to edit multiple sets at once or to
	allow for saving, loading, and importing just as it works in the DressNPCs Handler Screen. I chose to make
	it the way it is now, but if there is a demand to change it, then I will change it back.

------------------ Deutsch --------------------------------------------------------------------------------------------

Anforderungen:
	Java 8



Allgemein:
    Wenn eine Komponente blau umrandet ist, ist diese momentan markiert.

    Wenn eine Komponente rot umrandet ist, dann gibt es mit dem entsprechenden Feld einen Fehler. In einigen F�llen, zum
    Beispiel, wenn es einen Fehler bez�glich der Login-Informationen gibt, dann zeigen mehrere Felder einen Fehler. In
    diesen Feldern gibt es keine Information, welches Feld den Fehler ausgel�st hat-
    Wenn ein Fehler auftritt, findest du (hoffentlich) eine helfende Beschreibung des Problems, wenn du mit der Maus
    �ber ein Feld gehst und den Tooltip liest.

    So ziemlich jede Komponente hat einen Tooltip, ich empfehle diese zu lesen, wenn du mehr Informationen �ber ein Feld
    brauchst.



Login-Bildschirm:
    Gebe alle ben�tigten Informationen ein und dr�cke auf Best�tigen. Wenn eine Datenbankverbindung aufgebaut werden
    konnte, wirst du in das Hauptprogramm geleitet.

    Wenn du auf Speichern dr�ckst, werden alle Informationen, au�er dein Passwort, gespeichert. Wenn du das n�chste mal
    das Programm startest, sollte dein Passwortfeld rot markiert sein (Mach dir keine Gedanken, falls es nicht so sein
    sollte). Gib es einfach erneut ein und dr�cke auf best�tigen.



DressNPC Handler-Bildschirm:
    Jede Informationen wird auf 0 gesetzt, wenn sie nicht gesetzt wird. Du kannst also aus Best�tigen dr�cken und einige
    Felder leer lassen.

    Gebe die Informationen deines NPCs ein und dr�cke dann auf Best�tigen, um es zur Datenbank zu senden.

    Wenn es bereits einen DressNPC in der Datenbank mit der Modell-ID gibt, dann werden alle vorhandenen Daten mit
    deinen neuen Daten �berschrieben.



Kreaturwaffenverwalter-Bildschirm:

    Es gibt vier verschiedene Ausr�stungssets in diesem Bildschirm. Du kannst von 1 bis 4 alle benutzen. Wenn du einige
    von Ihnen leer l�sst und auf Best�tigen dr�ckst, wird ihr NPC Entry-ID- und Ausr�stungsset-ID-Feld als Fehlerhaft
    angezeigt. Dies hat keine Bedeutung, so funktioniert dieses Programm nunmal. Du kannst diese Fehler ignorieren.

    Jeder NPC in der creature_template-Tabelle kann eine beliebige Anzahl an Ausr�stungssets haben. Ein Set wird
    zuf�llig bei jedem Spawn der Kreatur ausgew�hlt. Die NPC Entry-ID korrespondiert mit der Entry-ID in der
    creature_template-Tabelle.

    Die Ausr�stungsset-IDs beginnen bei 1.

    Wegen der Programmkomplexit�t musste ich w�hlen, ob der Benutzer mehrere Sets auf einmal editieren kann, oder ob er
    in den DressNPC Verwaltungsbildschirm Speichern, Laden und Importieren kann. Ich habe so entschieden, wie es jetzt
    ist, aber wenn es ben�tigt wird, dann werde ich die �nderung wieder entfernen.