const AWS = require('aws-sdk')

AWS.config.update({ region: 'eu-central-1' })

const sqs = new AWS.SQS()

function push (queueName, msg) {
  const params = {
    QueueName: queueName
  }

  return new Promise((resolve, reject) => {
    sqs.getUser(params, (err, data) => {
      if(err) return reject(err)
      else {
        const params = {
          MessageBody: JSON.stringify(msg),
          QueueUrl: data.QueueUrl
        }

        sqs.sendMessage(params, (err) => {
          if(err) return reject(err)
          else resolve()
        })
      }
    })
  })
}

module.exports = { push }
