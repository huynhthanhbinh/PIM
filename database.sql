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
('THQ', 'THINH', 'HUYNH QUOC', '1998-09-26'),
('TBH', 'TOAN', 'BUI HUY', '1998-01-13'),
('TDT', 'TOAN', 'DINH TRAN', '1998-04-12'),
('TLV', 'TOI', 'LAM VAN', '1998-11-28'),
('TNM', 'TRI', 'NGUYEN MINH', '1998-06-12'),
('LLG', 'LAP', 'LY GIA', '1998-02-12'),
('LNT', 'LONG', 'NGUYEN THANH', '1998-10-28'),
('MTH', 'MINH', 'TO HONG DAI', '1998-10-31'),
('NTT', 'NGHIA', 'TO THANH', '1998-06-29'),
('NNH', 'NGHIA', 'NGUYEN HUU', '1998-02-12'),
('NPH', 'NGHIA', 'PHAM HUU', '1998-11-15');

INSERT INTO [GROUP](GROUP_LEADER_ID) VALUES
(13),(14),(15),(16);

INSERT INTO PROJECT(GROUP_ID, PROJECT_NUMBER, NAME, CUSTOMER, STATUS, START_DATE, END_DATE) VALUES
(1, 1000, 'PIM-1ST', 'ELCA', 'FIN', '2016-01-01', '2016-12-31'),
(2, 2000, 'PIM-2ND', 'ELCA', 'FIN', '2016-01-01', '2016-12-31'),
(3, 3000, 'PIM-3RD', 'ELCA', 'FIN', '2016-01-01', '2016-12-31'),
(4, 4000, 'PIM-4TH', 'ELCA', 'FIN', '2016-01-01', '2016-12-31'),
(1, 1001, 'IPENSION-1ST', 'THE GOVERNMENT', 'INP', '2017-01-01', NULL),
(2, 2001, 'IPENSION-2ND', 'THE GOVERNMENT', 'INP', '2017-01-01', NULL),
(3, 3001, 'IPENSION-3RD', 'THE GOVERNMENT', 'INP', '2017-01-01', NULL),
(4, 4001, 'IPENSION-4TH', 'THE GOVERNMENT', 'INP', '2017-01-01', NULL),
(1, 1002, 'GUARANTEE-INSURANCE-1ST', 'PRUDENTIAL', 'PLA', '2018-01-01', NULL),
(2, 2002, 'GUARANTEE-INSURANCE-2ND', 'PRUDENTIAL', 'PLA', '2018-01-01', NULL),
(3, 3002, 'GUARANTEE-INSURANCE-3RD', 'PRUDENTIAL', 'PLA', '2018-01-01', NULL),
(4, 4002, 'GUARANTEE-INSURANCE-4TH', 'PRUDENTIAL', 'PLA', '2018-01-01', NULL),
(1, 1003, 'DELIVERY-SERVICE-1ST', 'GRAB HOLDINGS INC', 'NEW', '2019-01-01',NULL),
(2, 2003, 'DELIVERY-SERVICE-2ND', 'GRAB HOLDINGS INC', 'NEW', '2019-01-01', NULL),
(3, 3003, 'DELIVERY-SERVICE-3RD', 'GRAB HOLDINGS INC', 'NEW', '2019-01-01', NULL),
(4, 4003, 'DELIVERY-SERVICE-4TH', 'GRAB HOLDINGS INC', 'NEW', '2019-01-01', NULL),
(1, 1004, 'FOOD-ORDERING-1ST', 'GRAB HOLDINGS INC', 'PLA', '2019-01-01', NULL),
(2, 2004, 'FOOD-ORDERING-2ND', 'GRAB HOLDINGS INC', 'PLA', '2019-01-01', NULL),
(3, 3004, 'FOOD-ORDERING-3RD', 'GRAB HOLDINGS INC', 'PLA', '2019-01-01', NULL),
(4, 4004, 'FOOD-ORDERING-4TH', 'GRAB HOLDINGS INC', 'PLA', '2019-01-01', NULL),
(1, 1005, 'PUBLIC-TRANSPORT-1ST', 'THE GOVERNMENT', 'NEW', '2018-05-30', NULL),
(2, 2005, 'PUBLIC-TRANSPORT-2ND', 'THE GOVERNMENT', 'NEW', '2018-05-30', NULL),
(3, 3005, 'PUBLIC-TRANSPORT-3RD', 'THE GOVERNMENT', 'NEW', '2018-05-30', NULL),
(4, 4005, 'PUBLIC-TRANSPORT-4TH', 'THE GOVERNMENT', 'NEW', '2018-05-30', NULL),
(1, 1006, 'EMPLOYEE-MANAGEMENT-1ST', 'ELCA', 'INP', '2018-12-24', NULL),
(2, 2006, 'EMPLOYEE-MANAGEMENT-2ND', 'ELCA', 'INP', '2018-12-24', NULL),
(3, 3006, 'EMPLOYEE-MANAGEMENT-3RD', 'ELCA', 'INP', '2018-12-24', NULL),
(4, 4006, 'EMPLOYEE-MANAGEMENT-4TH', 'ELCA', 'INP', '2018-12-24', NULL),
(1, 1007, 'CUSTOMER-FOLLOWING-1ST', 'PRUDENTIAL', 'FIN', '2014-01-01', '2017-12-31'),
(2, 2007, 'CUSTOMER-FOLLOWING-2ND', 'PRUDENTIAL', 'FIN', '2014-01-01', '2017-12-31'),
(3, 3007, 'CUSTOMER-FOLLOWING-3RD', 'PRUDENTIAL', 'FIN', '2014-01-01', '2017-12-31'),
(4, 4007, 'CUSTOMER-FOLLOWING-4TH', 'PRUDENTIAL', 'FIN', '2014-01-01', '2017-12-31');

/*
	int k = 0;
    for (int i = 1; i < 33; i++) {
        switch ((i % 4)) {
            case 1: {
                System.out.print("(" + i + ", 13), ");
                break;
            }
            case 2: {
                System.out.print("(" + i + ", 14), ");
                break;
            }
            case 3: {
                System.out.print("(" + i + ", 15), ");
                break;
            }
            default: {
                System.out.print("(" + i + ", 16), ");
                break;
            }
        }
        for (int j = 1; j < 11; j++) {
            int member = 10 * k + j;

            if (member < 13 || member > 16) {
                System.out.print("(" + i + ", " + member + "), ");
            }

        }
        k = (k < 4) ? (k + 1) : 0;
        System.out.println();
    }
*/

INSERT INTO PROJECT_EMPLOYEE(PROJECT_ID, EMPLOYEE_ID) VALUES
(1, 13), (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), 
(2, 14), (2, 11), (2, 12), (2, 17), (2, 18), (2, 19), (2, 20), 
(3, 15), (3, 21), (3, 22), (3, 23), (3, 24), (3, 25), (3, 26), (3, 27), (3, 28), (3, 29), (3, 30), 
(4, 16), (4, 31), (4, 32), (4, 33), (4, 34), (4, 35), (4, 36), (4, 37), (4, 38), (4, 39), (4, 40), 
(5, 13), (5, 41), (5, 42), (5, 43), (5, 44), (5, 45), (5, 46), (5, 47), (5, 48), (5, 49), (5, 50), 
(6, 14), (6, 1), (6, 2), (6, 3), (6, 4), (6, 5), (6, 6), (6, 7), (6, 8), (6, 9), (6, 10), 
(7, 15), (7, 11), (7, 12), (7, 17), (7, 18), (7, 19), (7, 20), 
(8, 16), (8, 21), (8, 22), (8, 23), (8, 24), (8, 25), (8, 26), (8, 27), (8, 28), (8, 29), (8, 30), 
(9, 13), (9, 31), (9, 32), (9, 33), (9, 34), (9, 35), (9, 36), (9, 37), (9, 38), (9, 39), (9, 40), 
(10, 14), (10, 41), (10, 42), (10, 43), (10, 44), (10, 45), (10, 46), (10, 47), (10, 48), (10, 49), (10, 50), 
(11, 15), (11, 1), (11, 2), (11, 3), (11, 4), (11, 5), (11, 6), (11, 7), (11, 8), (11, 9), (11, 10), 
(12, 16), (12, 11), (12, 12), (12, 17), (12, 18), (12, 19), (12, 20), 
(13, 13), (13, 21), (13, 22), (13, 23), (13, 24), (13, 25), (13, 26), (13, 27), (13, 28), (13, 29), (13, 30), 
(14, 14), (14, 31), (14, 32), (14, 33), (14, 34), (14, 35), (14, 36), (14, 37), (14, 38), (14, 39), (14, 40), 
(15, 15), (15, 41), (15, 42), (15, 43), (15, 44), (15, 45), (15, 46), (15, 47), (15, 48), (15, 49), (15, 50), 
(16, 16), (16, 1), (16, 2), (16, 3), (16, 4), (16, 5), (16, 6), (16, 7), (16, 8), (16, 9), (16, 10), 
(17, 13), (17, 11), (17, 12), (17, 17), (17, 18), (17, 19), (17, 20), 
(18, 14), (18, 21), (18, 22), (18, 23), (18, 24), (18, 25), (18, 26), (18, 27), (18, 28), (18, 29), (18, 30), 
(19, 15), (19, 31), (19, 32), (19, 33), (19, 34), (19, 35), (19, 36), (19, 37), (19, 38), (19, 39), (19, 40), 
(20, 16), (20, 41), (20, 42), (20, 43), (20, 44), (20, 45), (20, 46), (20, 47), (20, 48), (20, 49), (20, 50), 
(21, 13), (21, 1), (21, 2), (21, 3), (21, 4), (21, 5), (21, 6), (21, 7), (21, 8), (21, 9), (21, 10), 
(22, 14), (22, 11), (22, 12), (22, 17), (22, 18), (22, 19), (22, 20), 
(23, 15), (23, 21), (23, 22), (23, 23), (23, 24), (23, 25), (23, 26), (23, 27), (23, 28), (23, 29), (23, 30), 
(24, 16), (24, 31), (24, 32), (24, 33), (24, 34), (24, 35), (24, 36), (24, 37), (24, 38), (24, 39), (24, 40), 
(25, 13), (25, 41), (25, 42), (25, 43), (25, 44), (25, 45), (25, 46), (25, 47), (25, 48), (25, 49), (25, 50), 
(26, 14), (26, 1), (26, 2), (26, 3), (26, 4), (26, 5), (26, 6), (26, 7), (26, 8), (26, 9), (26, 10), 
(27, 15), (27, 11), (27, 12), (27, 17), (27, 18), (27, 19), (27, 20), 
(28, 16), (28, 21), (28, 22), (28, 23), (28, 24), (28, 25), (28, 26), (28, 27), (28, 28), (28, 29), (28, 30), 
(29, 13), (29, 31), (29, 32), (29, 33), (29, 34), (29, 35), (29, 36), (29, 37), (29, 38), (29, 39), (29, 40), 
(30, 14), (30, 41), (30, 42), (30, 43), (30, 44), (30, 45), (30, 46), (30, 47), (30, 48), (30, 49), (30, 50), 
(31, 15), (31, 1), (31, 2), (31, 3), (31, 4), (31, 5), (31, 6), (31, 7), (31, 8), (31, 9), (31, 10), 
(32, 16), (32, 11), (32, 12), (32, 17), (32, 18), (32, 19), (32, 20);

SELECT IDENT_CURRENT('EMPLOYEE') + 1;
SELECT IDENT_CURRENT('[GROUP]') + 1;
SELECT IDENT_CURRENT('PROJECT') + 1;
SELECT IDENT_CURRENT('PROJECT_EMPLOYEE') + 1;

SELECT * FROM EMPLOYEE;
SELECT * FROM [GROUP];
SELECT * FROM PROJECT;
SELECT * FROM PROJECT_EMPLOYEE;

SELECT PROJECT_NUMBER AS NUMBER FROM PROJECT;