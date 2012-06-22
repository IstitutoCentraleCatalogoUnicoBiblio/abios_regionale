# Set a null id_utenti in tabella biblioteca
update biblioteca set id_utenti = null;

# Annulla tutti i profili assegnati agli utenti esistenti precedentemente
truncate utenti_has_profili;

# Elimino il profilo 'CREAZIONE BIBLIOTECA'
delete from utenti_has_profili where id_profili = 2;
delete from profili where id_profili = 2;

# Annulla tutti i gestori delle biblioteche regionali
truncate biblioteca_has_utenti;

# Elimino tutti gli utenti preesistenti
truncate utenti;

# Inserisco unico utente 'regione'
insert into utenti (login, password, email) values ('regione', MD5('regione'), 'andrea.giuliano@beniculturali.it');
insert into utenti (login, password, email) values ('INERA', MD5('piropiro'), 'm.bartolozzi@inera.it');

# Assegno tutti i profili
insert into utenti_has_profili values(1, 1);
insert into utenti_has_profili values(1, 3);
insert into utenti_has_profili values(1, 4);
insert into utenti_has_profili values(1, 5);
insert into utenti_has_profili values(1, 6);
insert into utenti_has_profili values(1, 7);
insert into utenti_has_profili values(1, 8);
insert into utenti_has_profili values(1, 9);
insert into utenti_has_profili values(1, 10);
insert into utenti_has_profili values(2, 1);
insert into utenti_has_profili values(2, 3);
insert into utenti_has_profili values(2, 4);
insert into utenti_has_profili values(2, 5);
insert into utenti_has_profili values(2, 6);
insert into utenti_has_profili values(2, 7);
insert into utenti_has_profili values(2, 8);
insert into utenti_has_profili values(2, 9);
insert into utenti_has_profili values(2, 10);


