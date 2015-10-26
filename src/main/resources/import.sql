-- TYPE RESSOURCE
INSERT INTO TYPE_RESSOURCE (NOM) VALUES ('Salle');
INSERT INTO TYPE_RESSOURCE (NOM) VALUES ('Voiture');
INSERT INTO TYPE_RESSOURCE (NOM) VALUES ('Vidéo projecteur');

-- UTILISATEURS
INSERT INTO UTILISATEUR (LOGIN, PASSWORD, NOM, PRENOM, EMAIL, TELEPHONE, ADMIN) VALUES ('mclaveau','mclaveau','Claveau','Maxime','mclaveau@mail.com','0605040302',true);
INSERT INTO UTILISATEUR (LOGIN, PASSWORD, NOM, PRENOM, EMAIL, TELEPHONE, ADMIN) VALUES ('pcailleaud','pcailleaud','Cailleaud','Pierre','pcailleaud@mail.com','0607080910',false);

-- RESSOURCES
INSERT INTO RESSOURCE (DESCRIPTION, LOCALISATION, NOM, RESPONSABLE, TYPE) VALUES ('Salle B200','2eme étage EMN','Salle B200', 1, 1);
INSERT INTO RESSOURCE (DESCRIPTION, LOCALISATION, NOM, RESPONSABLE, TYPE) VALUES ('Salle B202','2eme étage EMN','Salle B202', 1, 1);
INSERT INTO RESSOURCE (DESCRIPTION, LOCALISATION, NOM, RESPONSABLE, TYPE) VALUES ('Salle B204','2eme étage EMN','Salle B204', 1, 1);
INSERT INTO RESSOURCE (DESCRIPTION, LOCALISATION, NOM, RESPONSABLE, TYPE) VALUES ('Voiture Clio 1','Garage EMN','Clio', 2, 2);
INSERT INTO RESSOURCE (DESCRIPTION, LOCALISATION, NOM, RESPONSABLE, TYPE) VALUES ('Voiture Porshe 1','Garage EMN','Porshe', 2, 2);
INSERT INTO RESSOURCE (DESCRIPTION, LOCALISATION, NOM, RESPONSABLE, TYPE) VALUES ('Voiture Golf 1','Garage EMN','Golf', 2, 2);
INSERT INTO RESSOURCE (DESCRIPTION, LOCALISATION, NOM, RESPONSABLE, TYPE) VALUES ('Video Projecteur 01','Acceuil EMN','Video Projecteur 01', 1, 3);
INSERT INTO RESSOURCE (DESCRIPTION, LOCALISATION, NOM, RESPONSABLE, TYPE) VALUES ('Video Projecteur 02','Acceuil EMN','Video Projecteur 02', 2, 3);
INSERT INTO RESSOURCE (DESCRIPTION, LOCALISATION, NOM, RESPONSABLE, TYPE) VALUES ('Video Projecteur 03','Acceuil EMN','Video Projecteur 03', 1, 3);

-- RESERVATIONS
INSERT INTO RESERVATION (DATEDEBUT, DATEFIN, RESSOURCE, UTILISATEUR) VALUES ('2015-11-1','2015-11-10',1, 1);