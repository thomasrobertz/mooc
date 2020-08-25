const AWS = require('aws-sdk')

AWS.config.update({ region: 'eu-central-1' })

const sns = new AWS.SNS()
const TOPIC_ARN = 'arn:aws:sns:eu-central-1:425906221170:hamster-topic'

function publish (msg) {
  const params = {
    TopicArn: TOPIC_ARN,
    Message: msg
  }

  return new Promise((resolve, reject) => {
    sns.publish(params, (err, data) => {
      if (err) reject(err)
      else resolve(data)
    })
  })
}

module.exports = { publish }
