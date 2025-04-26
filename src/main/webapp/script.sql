CREATE TABLE Transfers (
                           transfer_id INT IDENTITY(1,1) PRIMARY KEY,
                           student_id VARCHAR(20) NOT NULL,
                           full_name VARCHAR(100) NOT NULL,
                           desired_polytechnic VARCHAR(100) NOT NULL,
                           reason TEXT NOT NULL,
                           guardian_name VARCHAR(100) NOT NULL,
                           contact_number VARCHAR(20) NOT NULL,
                           email VARCHAR(100) NOT NULL,
                           submission_date DATETIME DEFAULT GETDATE(),
                           status VARCHAR(20) DEFAULT 'Pending'
);

