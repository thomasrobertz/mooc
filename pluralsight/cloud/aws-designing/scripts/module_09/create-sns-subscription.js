// Imports
const AWS = require('aws-sdk')

AWS.config.update({ region: 'eu-central-1' })

// Declare local variables
const sns = new AWS.SNS()
const type = 'email'
const endpoint = 'thomas.robertz@googlemail.com'
const topicArn = 'arn:aws:sns:eu-central-1:425906221170:hamster-topic'

createSubscription(type, topicArn, endpoint)
.then(data => console.log(data))

function createSubscription (type, topicArn, endpoint) {
  const params = {
    Protocol: type,
    TopicArn: topicArn,
    Endpoint: endpoint
  }

  return new Promise((resolve, reject) => {
    sns.subscribe(params, (err, data) => {
      if (err) reject(err)
      else resolve(data)
    })
  })
}
