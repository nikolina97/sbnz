--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'ROLE_ADMIN'), (2, 'ROLE_CLIENT');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'123-32-333', 676), (2, '123-123-123-0', 1800), (3, '111-111-111-1', 5000), (4, '123-123-100-0', 10000);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user`(dtype, id, first_name, last_name, password, username) VALUES ('Admin', 1,'Marko','Markovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','marko');
INSERT INTO `user`(dtype, id, first_name, last_name, password, username, account_opening_date, date_of_birth, jmbg, monthly_income, monthly_outcome, reward_points, account_id, active) 
VALUES ('Client', 2,'Jovan','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','jovan', '2017-03-04', '1977-03-04', '020202020', 2000, 0, 0, 1, _binary '' ), 
('Client',3,'Dejan','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','dejan', '2017-03-04', '1948-06-06', '00606976ds123', 2000, 100, 15, 2, _binary ''),
('Client', 4,'Dragan','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','dragan', '2017-03-04', '1968-06-06', '00606976dsa123', 2000, 0, 15, 3, _binary ''),
('Client', 5,'Jelena','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','jelena', '2017-03-04', '1968-06-06', '00606976ds12a3', 2000, 0, 15, 4, b'0');
--(3,'Dejan','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','dejan'), (4,'Dragan','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','dragan'), (5,'Jelena','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','jelena');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `client`
--

--LOCK TABLES `client` WRITE;
--/*!40000 ALTER TABLE `client` DISABLE KEYS */;
--INSERT INTO `client` VALUES (1,'Jovan','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','jovan', '2017-03-04', '1958-06-06', '00606976123', 2000, 300, 10, 1),
--(2,'Dejan','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','dejan', '2017-03-04', '1968-06-06', '00606976ds123', 2000, 0, 15, 2),
--(3,'Dragan','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','dragan', '2017-03-04', '1968-06-06', '00606976dsa123', 2000, 0, 15, 2),
--(4,'Jelena','Jankovic','$2a$04$CKjDkEtBltT5FYXDl2vM.uUxuTDE7oT2eqlHnzqE7SWQ6IVJC2Tri','jelena', '2017-03-04', '1968-06-06', '00606976ds12a3', 2000, 0, 15, 2);
--/*!40000 ALTER TABLE `client` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES (1,1), (2,2), (3,2), (4,2), (5,2);
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;

insert into  `warrantly` values (1, null, true);
/*insert into  `warrantly` values (2, 2, true);*/
insert into `credit_request` values(1, 'Housing', 36, 100000, 2, 1);
insert into `credit_request` values(2, 'Consumer', 36, 1000, 2, 1);
insert into `credit_request` values(3, 'Consumer', 36, 1000, 2, 1);
insert into `credit_request` values(4, 'Housing', 36, 10000, 2, 1);
insert into `credit_request` values(5, 'Consumer', 36, 30000, 2, 1);
insert into `credit_request` values(6, 'Housing', 36, 55000, 2, 1);
--insert into `contract` values(1, _binary '', 2, 200, 20000, _binary '', '2018-03-04', 2, 1);
insert into `contract` values(2, b'0', 2, 20, 200, _binary '', '2018-03-04', 3, 2);
insert into `contract` values(3, b'0', 2, 20, 200, _binary '', '2018-03-04', 3, 3);
insert into `contract` values(4, b'0', 2, 20, 200, _binary '', '2018-03-04', 2, 4);
insert into `contract` values(5, _binary '', 2, 20, 200, _binary '', '2019-06-04', 3, 5);
insert into `contract` values(6, _binary '', 2, 20, 200, _binary '', '2016-03-04', 2, 6);

