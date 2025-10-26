# Laboratorijske vežbe – dvočas br. 7

## Zadatak 1
*(radi laborant u saradnji sa studentima)*

Napraviti javnu klasu **Vozilo** u paketu **zadatak1** koja ima:
1. Privatni atribut **registarskiBroj** koji predstavlja registarski broj vozila (String).
2. Odgovarajuće javne **get i set metode** za ovaj atribut.
3. Redefinisanu javnu metodu **equals** klase Object. Metoda prvo proverava da li je uneti objekat klase **Vozilo**, pa ako nije, vraća FALSE. Metoda vraća TRUE ako je registarski broj vozila jednak registarskom broju unetog vozila, a inače vraća FALSE.
4. Redefinisanu javnu metodu **toString** koja vraća String sa podacima o vozilu registarskim brojem vozila u formatu: "Registarski broj: XXXXXXX".

Napraviti klasu **ProbaGaraza** u paketu **zadatak1** u kojoj se kreiraju dva automobila sa tablicama "BG123-AB" i "NS456-CD".

Napraviti javnu klasu **ParkingMesto** u paketu **zadatak1** koja ima:
1. Privatni atribut **slobodno** koji predstavlja indikator zauzetosti parking mesta. Ovaj indikator ima vrednost TRUE ako je mesto slobodno a FALSE ako nije. 
2. Privatni atribut **vozilo** koji predstavlja instancu klase **Vozilo** koje se nalazi na tom parking mestu.
3. Odgovarajuće javne **get i set metode** za ova dva atributa.
4. Redefinisanu javnu metodu **toString** koja vraća String sa podacima o parking mestu. Ako je mesto slobodno, metoda vraća tekst "SLOBODNO". Ako je mesto zauzeto, metoda vraća: "ZAUZETO, Registarski broj: XXXXXXX".

Napraviti javnu klasu **Garaza** u paketu **zadatak1** koja ima:
1. Privatni atribut **mesta** koji predstavlja niz objekata klase **ParkingMesto**.
2. Javni **konstruktor** koji kao parametar prima broj koji predstavlja kapacitet garaže tj. ukupan broj parking mesta. Ako je uneti broj veći od nule, potrebno je inicijalizovati niz **mesta** na taj kapacitet. Ako je uneti broj nula ili manji od nule, kapacitet garaže se postavlja na 40 i ispisuje se poruka "GRESKA". U svakom slučaju je potrebno inicijalizovati svako parking mesto i postaviti ga da bude slobodno.
3. Javni **konstruktor** koji kao ulaz dobija niz objekata klase **ParkingMesto**. Ako uneti niz nije NULL, onda se postavlja kao vrednost atributa **mesta**. U suprotnom, ispisuje se poruka "GRESKA".
4. Javnu metodu **ispisi** koja ispisuje u konzoli podatke o svim parking mestima sa rednim brojevima parking mesta, na primer:
<ol>
  <li>ZAUZETO, Registarski broj: BG123-AB</li>
  <li>SLOBODNO</li>
  <li>.... </li>
</ol>
<br/>
5. Javnu metodu **uvedi** koja "uvodi" vozilo u garažu. Ova metoda kao parametar dobija objekat klase **Vozilo** koje je potrebno uvesti na prvo slobodno mesto. Ako slobodnih mesta nema, ispisati poruku "NEMA MESTA".
6. Javnu metodu **izvedi** koja "izvodi" vozilo iz garaže. Ova metoda kao parametar dobija objekat klase **Vozilo**. Ako se u garaži nalazi vozilo sa istim registarskim brojem, potrebno ga je "izvesti". Izvodjenje vozila podrazumeva da se mesto na kome je bilo označi kao slobodno i da se podatak o njegovom vozilu ukloni (postavi na NULL).

U prethodno kreiranoj klasi **ProbaGaraza** u paketu **zadatak1** kreirati jedan objekat klase **Garaza** kapaciteta 10 mesta i u njega uvesti dva automobila. Ispisati podatke u garaži. Nakon toga, izvesti prvi automobil i opet ispisati podatke o garaži.

## Zadatak 2
*(studenti rade sami)*

Napraviti klasu **Namirnica** u paketu **zadatak2** koja ima:
1. Privatne atribute **naziv** i **cena** koji predstavljaju naziv i cenu namirnice (double).
2. Odgovarajuće javne get i set metode za ova dva atributa.

Napraviti klasu **PotrosackaKorpa** u paketu **zadatak2** koja ima:
1. Privatni atribut **namirnice** koji predstavlja niz objekata klase **Namirnica**.
2. Javni konstruktor koji kao parametar prima broj koji predstavlja kapacitet korpe tj. ukupan broj namirnica koji moze da stane u korpu. Ako je uneti broj veći od nule, a manji od 20, potrebno je inicijalizovati atribut **namirnice** na taj broj. U suprotnom, kreirati niz kapaciteta 10 i ispisati poruku o grešci na ekranu.
3. Javnu metodu **dodajUKorpu** koja kao ulaz dobija objekat klase Namirnica koji treba da doda u korpu. Namirnicu treba dodati na prvo slobodno u korpi. Ako slobodnih mesta nema, ispisati poruku "NEMA MESTA".
4. Javnu metodu **izracunajUkupnuCenu** koja izračunava i vraća ukupnu cenu svih namirnica u korpi. Obratiti pažnju da u nizu može biti praznih mesta.
5. Javnu metodu **pronadjiNajskuplju** koja vraća objekat klase **Namirnica** koji ima najvišu cenu u korpi. U slučaju da je korpa prazna, vratiti NULL. U slučaju da postoji više namirnica sa istom cenom, vratiti bilo koju. Obratiti pažnju da u nizu može biti praznih mesta.

Napraviti klasu **ProbaPotrosackaKorpa** u paketu **zadatak2** koja kreira jedan objekat klase **PotrosackaKorpa** kapaciteta 15. U korpu dodati sledece namirnice: "hleb" 50 din, "mleko" 80 din, "cokolada" 100 din. Ispisati na ekranu ukupnu cenu i naziv najskuplje namirnice.