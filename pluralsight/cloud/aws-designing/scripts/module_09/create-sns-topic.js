// Imports
const AWS = require('aws-sdk')

AWS.config.update({ region: 'eu-central-1' })

// Declare local variables
const sns = new AWS.SNS()
const topicName = 'hamster-topic'

createTopic(topicName)
.then(data => console.log(data))

function createTopic (topicName) {
  const params = {
    Name: topicName
  }

  console.log("Attempting to create topic")

  return new Promise((resolve, reject) => {
    sns.createTopic(params, (err, data) => {
      if (err) reject(err)
      else resolve(data)
    })
  }).catch(error => { console.log('caught: ', error.message); });
}
