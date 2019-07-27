CREATE DATABASE PIM

GO

USE PIM

CREATE TABLE EMPLOYEE (
	ID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	VISA CHAR(3) NOT NULL UNIQUE,
	FIRST_NAME VARCHAR(50) NOT NULL,
	LAST_NAME VARCHAR(50) NOT NULL,
	BIRTH_DATE DATE NOT NULL,
)

CREATE TABLE [GROUP] (
	ID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	GROUP_LEADER_ID BIGINT NOT NULL REFERENCES EMPLOYEE(ID) UNIQUE,
)

CREATE TABLE PROJECT (
	ID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	GROUP_ID BIGINT NOT NULL REFERENCES [GROUP](ID),
	PROJECT_NUMBER INT NOT NULL UNIQUE,
	NAME VARCHAR(50) NOT NULL,
	CUSTOMER VARCHAR(50) NOT NULL,
	STATUS CHAR(3) NOT NULL,
	START_DATE DATE NOT NULL,
	END_DATE DATE,
)

CREATE TABLE PROJECT_EMPLOYEE (
	ID BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	PROJECT_ID BIGINT NOT NULL REFERENCES PROJECT(ID),
	EMPLOYEE_ID BIGINT NOT NULL REFERENCES EMPLOYEE(ID),
)

INSERT INTO EMPLOYEE(VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES
('HUA', 'HAI', 'NGUYEN THANH', '1997-09-02'),
('BHT', 'BINH', 'HUYNH THANH', '1998-05-30'),
('LAV', 'LUC', 'PHAM VIET', '1998-01-18'),
('DRG', 'DUNG', 'TRAN CONG TAN', '1998-12-03'),
('DGG', 'DUY', 'NGUYEN GIAP PHUONG', '1998-09-20'),
('CAN', 'CANH', 'NGO ANH', '1998-05-19'),
('DNM', 'DUC', 'NGUYEN MINH', '1998-12-13'),
('NPT', 'NAM', 'PHAN TRAN XUAN', '1998-01-07'),
('PRH', 'PHONG', 'TRAN HAI', '1998-06-06'),
('KTM', 'KHA', 'TRAN MINH', '1998-10-28'),
('KDQ', 'KHAI', 'DAM QUANG', '1998-03-09'),
('TAV', 'THUC', 'HOANG VAN', '1998-09-19'),
('TRV', 'THOI', 'TRAN VAN', '1970-01-01'),
('TBG', 'BAO', 'NGUYEN TAN', '1970-01-01'),
('TTB', 'TUNG', 'BUI THANH', '1970-01-01'),
('VVH', 'VU', 'VO HOANG ANH', '1970-01-01'),
('NLQ', 'NHAT', 'LE QUANG', '1997-09-24'),
('DVM', 'DANG', 'VU MINH', '1998-12-21'),
('BVT', 'BACH', 'VU TUONG', '1998-12-11'),
('DPT', 'DUY', 'PHAN TRAN THE', '1998-09-23'),
('CTM', 'CANH', 'TRAN MINH', '1998-04-21'),
('ANB', 'AN', 'NGUYEN BINH', '1998-12-15'),
('BNT', 'BACH', 'NGUYEN LE TUNG', '1998-10-11'),
('NTH', 'NGOC', 'TRAN HANH', '1998-02-15'),
('NTM', 'NHUT', 'TRAN MINH', '1998-10-11'),
('PTV', 'PHAT', 'TRUONG VY', '1998-11-18'),
('PPA', 'PHU', 'PHAM ANH', '1998-11-20'),
('PND', 'PHU', 'NGHIEM DUC', '1998-01-01'),
('PTT', 'PHUC', 'TRAN TRONG', '1998-12-06'),
('PTD', 'PHUC', 'TRINH DAI', '1998-08-10'),
('CLN', 'CHINH', 'LUU NGHIEP', '1998-10-30'),
('HVU', 'HAI', 'VU', '1998-12-27'),
('CPV', 'CHUAN', 'PHAM VAN', '1998-09-29'),
('DPV', 'DAO', 'PHAM VIET MINH', '1998-05-21'),
('DNA', 'DAT', 'NGUYEN ANH', '1998-01-26'),
('HND', 'HAI', 'NGUYEN DUC', '1998-03-13'),
('QPN', 'QUANG', 'PHAN NHAT', '1998-10-22'),
('QNM', 'QUANG', 'NGUYEN MINH', '1998-04-24'),
('TDD', 'TAI', 'DANG DUC', '1998-06-07'),
('THQ', 'THINH', 'HUYNH QUOC', '1998-09-26');

INSERT INTO [GROUP](GROUP_LEADER_ID) VALUES
(13),(14),(15);

INSERT INTO PROJECT(GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE) VALUES
(1, 1000, 'PIM-1ST', 'ELCA', 'FIN', '2016-01-01', '2016-12-31'),
(2, 2000, 'PIM-2ND', 'ELCA', 'FIN', '2016-01-01', '2016-12-31'),
(3, 3000, 'PIM-3RD', 'ELCA', 'FIN', '2016-01-01', '2016-12-31'),
(1, 1001, 'IPENSION-1ST', 'THE GOVERNMENT', 'INP', '2017-01-01', NULL),
(2, 2001, 'IPENSION-2ND', 'THE GOVERNMENT', 'INP', '2017-01-01', NULL),
(3, 3001, 'IPENSION-3RD', 'THE GOVERNMENT', 'INP', '2017-01-01', NULL),
(1, 1002, 'GUARANTEE-INSURANCE-1ST', 'PRUDENTIAL', 'PLA', '2018-01-01', NULL),
(2, 2002, 'GUARANTEE-INSURANCE-2ND', 'PRUDENTIAL', 'PLA', '2018-01-01', NULL),
(3, 3002, 'GUARANTEE-INSURANCE-3RD', 'PRUDENTIAL', 'PLA', '2018-01-01', NULL),
(1, 1003, 'DELIVERY-SERVICE-1ST', 'GRAB HOLDINGS INC', 'NEW', '2019-01-01',NULL),
(2, 2003, 'DELIVERY-SERVICE-2ND', 'GRAB HOLDINGS INC', 'NEW', '2019-01-01', NULL),
(3, 3003, 'DELIVERY-SERVICE-3RD', 'GRAB HOLDINGS INC', 'NEW', '2019-01-01', NULL),
(1, 1004, 'FOOD-ORDERING-1ST', 'GRAB HOLDINGS INC', 'PLA', '2019-01-01', NULL),
(2, 2004, 'FOOD-ORDERING-2ND', 'GRAB HOLDINGS INC', 'PLA', '2019-01-01', NULL),
(3, 3004, 'FOOD-ORDERING-3RD', 'GRAB HOLDINGS INC', 'PLA', '2019-01-01', NULL),
(1, 1005, 'PUBLIC-TRANSPORT-1ST', 'THE GOVERNMENT', 'NEW', '2018-05-30', NULL),
(2, 2005, 'PUBLIC-TRANSPORT-2ND', 'THE GOVERNMENT', 'NEW', '2018-05-30', NULL),
(3, 3005, 'PUBLIC-TRANSPORT-3RD', 'THE GOVERNMENT', 'NEW', '2018-05-30', NULL),
(1, 1006, 'EMPLOYEE-MANAGEMENT-1ST', 'ELCA', 'INP', '2018-12-24', NULL),
(2, 2006, 'EMPLOYEE-MANAGEMENT-2ND', 'ELCA', 'INP', '2018-12-24', NULL),
(3, 3006, 'EMPLOYEE-MANAGEMENT-3RD', 'ELCA', 'INP', '2018-12-24', NULL),
(1, 1007, 'CUSTOMER-FOLLOWING-1ST', 'PRUDENTIAL', 'FIN', '2014-01-01', '2017-12-31'),
(2, 2007, 'CUSTOMER-FOLLOWING-2ND', 'PRUDENTIAL', 'FIN', '2014-01-01', '2017-12-31'),
(3, 3007, 'CUSTOMER-FOLLOWING-3RD', 'PRUDENTIAL', 'FIN', '2014-01-01', '2017-12-31');

/*
int k = 0;
        for (int i = 1; i < 25; i++) {
            switch ((i % 3)) {
                case 1: {
                    System.out.print("(" + i + ", 13), ");
                    break;
                }
                case 2: {
                    System.out.print("(" + i + ", 14), ");
                    break;
                }
                default: {
                    System.out.print("(" + i + ", 15), ");
                    break;
                }
            }
            for (int j = 1; j < 11; j++) {
                int member = 10 * k + j;

                if (!(member == 13 || member == 14 || member == 15)) {
                    System.out.print("(" + i + ", " + member + "), ");
                }

            }
            k = (k < 3) ? (k + 1) : 0;
            System.out.println();
        }
*/

INSERT INTO PROJECT_EMPLOYEE(PROJECT_ID, EMPLOYEE_ID) VALUES
(1, 13), (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), 
(2, 14), (2, 11), (2, 12), (2, 16), (2, 17), (2, 18), (2, 19), (2, 20), 
(3, 15), (3, 21), (3, 22), (3, 23), (3, 24), (3, 25), (3, 26), (3, 27), (3, 28), (3, 29), (3, 30), 
(4, 13), (4, 31), (4, 32), (4, 33), (4, 34), (4, 35), (4, 36), (4, 37), (4, 38), (4, 39), (4, 40), 
(5, 14), (5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (5, 6), (5, 7), (5, 8), (5, 9), (5, 10), 
(6, 15), (6, 11), (6, 12), (6, 16), (6, 17), (6, 18), (6, 19), (6, 20), 
(7, 13), (7, 21), (7, 22), (7, 23), (7, 24), (7, 25), (7, 26), (7, 27), (7, 28), (7, 29), (7, 30), 
(8, 14), (8, 31), (8, 32), (8, 33), (8, 34), (8, 35), (8, 36), (8, 37), (8, 38), (8, 39), (8, 40), 
(9, 15), (9, 1), (9, 2), (9, 3), (9, 4), (9, 5), (9, 6), (9, 7), (9, 8), (9, 9), (9, 10), 
(10, 13), (10, 11), (10, 12), (10, 16), (10, 17), (10, 18), (10, 19), (10, 20), 
(11, 14), (11, 21), (11, 22), (11, 23), (11, 24), (11, 25), (11, 26), (11, 27), (11, 28), (11, 29), (11, 30), 
(12, 15), (12, 31), (12, 32), (12, 33), (12, 34), (12, 35), (12, 36), (12, 37), (12, 38), (12, 39), (12, 40), 
(13, 13), (13, 1), (13, 2), (13, 3), (13, 4), (13, 5), (13, 6), (13, 7), (13, 8), (13, 9), (13, 10), 
(14, 14), (14, 11), (14, 12), (14, 16), (14, 17), (14, 18), (14, 19), (14, 20), 
(15, 15), (15, 21), (15, 22), (15, 23), (15, 24), (15, 25), (15, 26), (15, 27), (15, 28), (15, 29), (15, 30), 
(16, 13), (16, 31), (16, 32), (16, 33), (16, 34), (16, 35), (16, 36), (16, 37), (16, 38), (16, 39), (16, 40), 
(17, 14), (17, 1), (17, 2), (17, 3), (17, 4), (17, 5), (17, 6), (17, 7), (17, 8), (17, 9), (17, 10), 
(18, 15), (18, 11), (18, 12), (18, 16), (18, 17), (18, 18), (18, 19), (18, 20), 
(19, 13), (19, 21), (19, 22), (19, 23), (19, 24), (19, 25), (19, 26), (19, 27), (19, 28), (19, 29), (19, 30), 
(20, 14), (20, 31), (20, 32), (20, 33), (20, 34), (20, 35), (20, 36), (20, 37), (20, 38), (20, 39), (20, 40), 
(21, 15), (21, 1), (21, 2), (21, 3), (21, 4), (21, 5), (21, 6), (21, 7), (21, 8), (21, 9), (21, 10), 
(22, 13), (22, 11), (22, 12), (22, 16), (22, 17), (22, 18), (22, 19), (22, 20), 
(23, 14), (23, 21), (23, 22), (23, 23), (23, 24), (23, 25), (23, 26), (23, 27), (23, 28), (23, 29), (23, 30), 
(24, 15), (24, 31), (24, 32), (24, 33), (24, 34), (24, 35), (24, 36), (24, 37), (24, 38), (24, 39), (24, 40); 

SELECT IDENT_CURRENT('EMPLOYEE') + 1
SELECT IDENT_CURRENT('[GROUP]') + 1
SELECT IDENT_CURRENT('PROJECT') + 1
SELECT IDENT_CURRENT('PROJECT_EMPLOYEE') + 1

SELECT * FROM EMPLOYEE;
SELECT * FROM [GROUP];
SELECT * FROM PROJECT;
SELECT * FROM PROJECT_EMPLOYEE;

SELECT PROJECT_NUMBER AS NUMBER FROM PROJECT;