const Sequelize = require('sequelize')

const host = 'user.cytymxbwwegm.eu-central-1.rds.amazonaws.com'
const database = 'user'
const username = 'admin'
const password = 'mypassword'

const sequelize = new Sequelize(database, username, password, {
  host,
  dialect: 'mysql',
  pool: {
    max: 5,
    min: 0,
    idle: 5000
  }
})

module.exports = sequelize
