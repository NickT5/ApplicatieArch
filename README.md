# ApplicatieArch
Project

Omdat labo's, practica en oefeningen niet aan 140 studenten tegelijkertijd gegeven kunnen
worden, moeten de 1e bachelor studenten op Campus De Nayer onderverdeeld worden in een
aantal oefeningengroepen. Om het leven voor de studenten zo aangenaam mogelijk te maken,
wordt er hierbij rekening gehouden met hun wensen. Zo kunnen ze er bijvoorbeeld voor zorgen
dat ze in dezelfde groep zitten als een vriend met wie ze regelmatig carpoolen, of kunnen ze ook
opgeven dat ze met bepaalde mensen juist niet in een groep willen zitten. De bedoeling van dit
project is om een softwaresysteem te maken dat dit proces ondersteunt.
Het systeem moet zowel gebruikt kunnen worden door studenten als docenten. Elk van de
gebruikers moet een login hebben op het systeem en, afhankelijk van of hij docent of student is,
moet hij terechtkomen bij de juiste functionaliteit.
Voor de studenten. Een student moet kunnen aangeven met welke van zijn medestudenten hij
wel of niet in de groep wil zitten. Zorg ervoor dat dit kan via een keuzemenu, zodat de student
bijvoorbeeld al geen fouten kan maken door een naam van een medestudent verkeerd in te tikken.
Om de interface vlot bruikbaar te maken, is het nuttig om wel toe te laten dat de student snel een
medestudent kan terugvinden op basis van een fragment van diens naam. De student moet op elk
moment altijd duidelijk kunnen zien over welke van zijn medestudenten hij al welke voorkeur heeft
ingegeven. Om fouten te voorkomen, moet er nog extra bevestigingsfase zijn, waarbij de student
nog eens een naal overzicht van zijn keuzes te zien krijgt en dan kan kiezen om dit denitief in
te sturen of toch maar niet. Eenmaal de student zijn voorkeuren denitief heeft ingestuurd, moet
hij zijn voorkeuren nog kunnen bekijken, maar mag hij deze niet meer wijzigen.
Voor de docenten. Een docent moet aan de groepsindeling kunnen werken. Hij vertrekt hiervoor
van een (initieel leeg) overzicht van de groepen die er zijn. Hij moet een nieuwe groep kunnen
1
bijmaken en een bestaande groep kunnen editeren. Bij het editeren van een groep, moet hij studenten
die er al inzitten weer kunnen verwijderen en studenten die er nog niet inzitten kunnen
toevoegen. Ook hier moet dit kunnen gebeuren d.m.v. keuzemenu's die vlot te doorzoeken zijn.
Er moet duidelijk te zien zijn welke studenten al in een groep zitten en welke nog niet. Op de
pagina van een groep moeten ook eventuele problemen met die groep duidelijk getoond worden:
als een student zelf nog niet in de groep zit, maar sommige van zijn aangeduide vrienden wel,
moet dit getoond worden (liefst op een zodanige manier dat het eenvoudig is om die student dan
zelf ook toe te voegen). Als een student in de groep zit met iemand waarmee hij expliciet niet
wou samenzitten, moet dit ook getoond worden. Tot slot moet de docent op de overzichtspagina
ook een idee krijgen hoeveel werk er nog te doen is om de groepsindeling af te werken: hiervoor
moet getoond worden hoeveel studenten van het totale aantal er nog moeten worden toegewezen,
en moet er voor elke groep ook getoond worden hoeveel problemen er met die groep nog zijn.
Achteraf. De docent moet de groepsindeling kunnen naliseren. Eenmaal als dit gebeurd is,
moet hij de groepsindeling nog kunnen bekijken, maar kan hij ze niet meer wijzigingen. Als de
groepsindeling genaliseerd is, verandert ook hetgeen de studenten te zien krijgen als ze inloggen:
nu krijgen ze hier hun eigen groep (= groepsnummer en medestudenten) te zien. De medestudenten
uit hun groep met met wie ze speciek wel of niet in de groep wouden zitten, moeten hierbij speciaal
worden aangeduid.
Eenmaal de groepsindeling genaliseerd is, moet deze ook nog gepubliceerd worden. Maak hiervoor
een standalone Java-programma dat als het wordt uitgevoerd de groepsindeling wegschrijft
naar een txt bestand, dat er bijvoorbeeld zo uitziet:
Groep 1
- Joost Vennekens
- An Van Haperen
Groep 2:
- Herman Crauwels
- Ann Philips
