var dbName = 'patientdb';
var user = 'user_patient';
var passwd = 'pass_patient';

db = db.getSiblingDB(dbName);
db.createUser({
    user: user,
    pwd: passwd,
    roles: [
        { role: 'readWrite', db: dbName }
    ]
});
