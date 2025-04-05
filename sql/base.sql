create database hospital;
use hospital;

create table especialidad(

	id int auto_increment not null,
    nombre varchar(32) not null,
    descripcion varchar(100) not null,
    constraint pk_especialidad primary key(id)
);

create table medico(

	cedula int unique not null,
    nombre varchar(32) not null,
    apellido varchar(32) not null,
    correo varchar(50) not null,
    especialidad_id int not null,
    constraint pk_medico primary key(cedula),
    constraint fk_especialidad foreign key(especialidad_id) references especialidad(id)
);

create table paciente(

	cedula int unique not null,
    nombre varchar(32) not null,
    apellido varchar(32) not null,
    correo varchar(50) not null,
	medico_id int not null,
    constraint pk_paciente primary key(cedula),
	constraint fk_medico foreign key(medico_id) references medico(cedula)
);

create table medicamento(

	id int auto_increment not null,
    nombre varchar(32) not null,
    dosis varchar(100) not null,
    constraint pk_medicamento primary key(id)
);

create table padecimiento(

	id int auto_increment not null,
    nombre varchar(32) not null,
    sintomas varchar(50) not null,
    descripcion varchar(100) not null,
    constraint pk_padecimiento primary key(id)
);

create table procedimiento(

	id int auto_increment not null,
	nombre varchar(32) not null,
    descripcion varchar(100) not null,
    constraint pk_procedimiento primary key(id)
);

create table expediente_medico(

	id int auto_increment not null,
    fecha date not null,
    paciente_id int not null,
    padecimiento_id int not null,
    medicamento_id int not null,
    procedimiento_id int not null,
	constraint pk_expediente_medico primary key(id),
	constraint fk_padecimiento foreign key (padecimiento_id) references padecimiento(id),
	constraint fk_medicamento foreign key (medicamento_id) references medicamento(id),
    constraint fk_procedimiento foreign key (procedimiento_id) references procedimiento(id)
    constraint fk_paciente_ex foreign key (paciente_id) references paciente(cedula)
);

create table cita(
	
    id int auto_increment not null,
    fecha datetime not null,
    paciente_id int not null,
    medico_id int not null,
    constraint pk_cita primary key(id),
    constraint fk_paciente foreign key(paciente_id) references paciente(cedula),
    constraint fk_medico_cita foreign key(medico_id) references medico(cedula)
);

create table agenda(
    id int auto_increment not null,
	paciente_id int not null,
    cita_id int not null,
    constraint pk_id primary key(id),
    constraint fk_paciente_ag foreign key(paciente_id) references paciente(cedula),
    constraint fk_cita foreign key(cita_id) references cita(id)
);

drop user if exists 'admin'@'localhost';
create user 'admin'@'localhost' identified by 'adminpass';
grant insert,delete,update,execute on hospital.* to 'admin'@'localhost';
grant select on hospital.* to 'admin'@'localhost';

INSERT INTO especialidad (nombre, descripcion) VALUES
('Cardiología', 'Especialidad médica que se encarga del tratamiento de las enfermedades del corazón.'),
('Dermatología', 'Especialidad médica que se dedica al estudio de la piel, cabello, uñas y mucosas.'),
('Gastroenterología', 'Especialidad médica que se ocupa del tratamiento de las enfermedades del aparato digestivo.'),
('Pediatría', 'Especialidad médica que se enfoca en la atención de niños y adolescentes.'),
('Neurología', 'Especialidad médica que se ocupa del estudio del sistema nervioso y sus enfermedades.');

INSERT INTO medico (cedula, nombre, apellido, correo, especialidad_id) VALUES
(123456, 'Juan', 'Pérez', 'juan.perez@example.com', 1),
(789012, 'María', 'González', 'maria.gonzalez@example.com', 2),
(345678, 'Carlos', 'Rodríguez', 'carlos.rodriguez@example.com', 3),
(901234, 'Ana', 'López', 'ana.lopez@example.com', 4),
(567890, 'Luis', 'Martínez', 'luis.martinez@example.com', 5);

INSERT INTO paciente (cedula, nombre, apellido, correo, medico_id) VALUES
(111111, 'Laura', 'Sánchez', 'laura.sanchez@example.com', 123456),
(222222, 'Pedro', 'Gómez', 'pedro.gomez@example.com', 789012),
(333333, 'Sofía', 'Hernández', 'sofia.hernandez@example.com', 345678),
(444444, 'Miguel', 'Díaz', 'miguel.diaz@example.com', 901234),
(555555, 'Isabel', 'Torres', 'isabel.torres@example.com', 567890);

INSERT INTO medicamento (nombre, dosis) VALUES
('Aspirina', '500 mg'),
('Paracetamol', '1000 mg'),
('Ibuprofeno', '400 mg'),
('Amoxicilina', '500 mg'),
('Omeprazol', '20 mg');

INSERT INTO padecimiento (nombre, sintomas, descripcion) VALUES
('Hipertensión', 'Presión arterial alta, dolor de cabeza', 'Trastorno crónico que afecta la presión arterial.'),
('Dermatitis', 'Picazón en la piel, enrojecimiento', 'Inflamación de la piel que puede causar picazón y enrojecimiento.'),
('Gastritis', 'Dolor abdominal, acidez', 'Inflamación de la mucosa gástrica que puede causar malestar estomacal.'),
('Gripe', 'Fiebre, congestión nasal', 'Enfermedad viral que afecta el sistema respiratorio.'),
('Epilepsia', 'Convulsiones, pérdida de conciencia', 'Trastorno neurológico que causa convulsiones y pérdida de conciencia.');

INSERT INTO procedimiento (nombre, descripcion) VALUES
('Cirugía de corazón', 'Procedimiento quirúrgico para tratar enfermedades cardíacas.'),
('Biopsia de piel', 'Extracción de una muestra de piel para su análisis.'),
('Endoscopia gástrica', 'Exploración visual del interior del estómago.'),
('Vacunación infantil', 'Aplicación de vacunas a niños para prevenir enfermedades.'),
('Electroencefalograma', 'Registro de la actividad eléctrica cerebral para diagnosticar trastornos neurológicos.');

INSERT INTO expediente_medico (fecha, paciente_id, padecimiento_id, medicamento_id, procedimiento_id) VALUES
('2023-09-01', 111111, 1, 1, 1),
('2023-09-02', 222222, 2, 2, 2),
('2023-09-03', 333333, 3, 3, 3),
('2023-09-04', 444444, 4, 4, 4),
('2023-09-05', 555555, 5, 5, 5);

INSERT INTO cita (fecha, paciente_id, medico_id) VALUES
('2023-09-10 10:00:00', 111111, 123456),
('2023-09-11 11:00:00', 222222, 789012),
('2023-09-12 12:00:00', 333333, 345678),
('2023-09-13 13:00:00', 444444, 901234),
('2023-09-14 14:00:00', 555555, 567890);

INSERT INTO agenda (paciente_id, cita_id) VALUES
(111111, 1),
(222222, 2),
(333333, 3),
(444444, 4),
(555555, 5);






