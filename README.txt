1. W onCreate przypisanie id komponent�w oraz dodanie listenera do przycisku, po kt�rego klikni�ciu nast�puje utworzenie 
obiektu klasy WebService i wywo�anie na jego rzecz metody execute() w przypadku, gdy istnieje po��czenie z intenretem.
2. Klasa WebService jest odpowiedzialna za po��czenie z us�ug�.
3. Metoda getFerenheit() odpowiedzialna za wys�anie stopni Celciusza do metody CelsiusToFahrenheit z webservicu,
pobranie odpowiedzi i przypisanie do pola fahrenheita. Wykorzystany protokol http.
4. Wykorzystane biblioteki kSOAP 2.