const dbName = 'patientdb';
const user = 'user_patient';
const passwd = 'pass_patient';

db = db.getSiblingDB(dbName);
db.createUser({
    user: user,
    pwd: passwd,
    roles: [
        { role: 'readWrite', db: dbName }
    ]
});
