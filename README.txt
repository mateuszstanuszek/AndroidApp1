1. W onCreate przypisanie id komponentów oraz dodanie listenera do przycisku, po którego klikniêciu nastêpuje utworzenie 
obiektu klasy WebService i wywo³anie na jego rzecz metody execute() w przypadku, gdy istnieje po³¹czenie z intenretem.
2. Klasa WebService jest odpowiedzialna za po³¹czenie z us³ug¹.
3. Metoda getFerenheit() odpowiedzialna za wys³anie stopni Celciusza do metody CelsiusToFahrenheit z webservicu,
pobranie odpowiedzi i przypisanie do pola fahrenheita. Wykorzystany protokol http.
4. Wykorzystane biblioteki kSOAP 2.