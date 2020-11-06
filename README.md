# Disclaimer
Wenn das Projekt bei euch nicht baut liegt es mit an Sicherheit an einem der folgenden Punkte
1. Das Projekt wurde nicht als Maven-Projekt in die Ide importiert
2. Die Maven Dependencies wurde nicht heruntergeladen
3. Das Lombok Plugin wurde nicht in die Ide hinzugefügt
4. Annotation Processing wurde in der Ide nicht aktiviert

## Maven
Maven ist ein Build-Management-Tool mit welchen man exteren Biblioteken relativ einfach in sein eigenes Projekt hinzufügen kann, ohne sich um nerviges Versionsmanagement der Abhängigkeiten (Dependencies) zu externen Libs kümmern zu müssen

## Lombok
Eine sehr nütliche Bibliotek, welche für einen das schreiben von generischen Code (Konstruktor, Getter, Setter) übernimmt. Dies bedeutet, dass man weder Konstruktoren noch Getter oder Setter schreiben muss, diese aber in den compilierten Code eingefügt werden, als würden sie in der Java Datei vorliegen.
Damit die Ide aber weiß, dass Getter und co nach dem Compilieren vorliegen muss man noch das Annotation Processing aktivieren sonst wird alles was Lombok später generiert als fehlend angezeigt.
