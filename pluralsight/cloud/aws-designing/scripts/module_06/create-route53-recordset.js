// Imports
const AWS = require('aws-sdk')

AWS.config.update({ region: 'eu-central-1' })

// Declare local variables
const route53 = new AWS.Route53()
const hzId = '/hostedzone/Z0631849MQX1HJLNL9VD'

createRecordSet(hzId)
.then(data => console.log(data))

function createRecordSet (hzId) {
  const params = {
    HostedZoneId: hzId,
    ChangeBatch: {
      Changes: [{
        Action: 'CREATE', 
        ResourceRecordSet: {
          Name: 'hbfl.online', 
          Type: 'A',
          AliasTarget: {
            DNSName: 'hamsterELB-569534087.eu-central-1.elb.amazonaws.com', 
            EvaluateTargetHealth: false,
            HostedZoneId: 'Z215JYRZR1TBD5'
          }
        }
      }]
    }
  }
  // TODO: Create params const
  // Link to ELB Regions:
  // https://docs.aws.amazon.com/general/latest/gr/elb.html

  return new Promise((resolve, reject) => {
    route53.changeResourceRecordSets(params, (err, data) => {
      if(err) reject(err)
      else resolve(data)
    })
  })
}
