// Imports
const AWS = require('aws-sdk')

AWS.config.update({ region: 'eu-central-1' })

// Declare local variables
const route53 = new AWS.Route53()
const hzId = '/hostedzone/Z2R1CQ04706J63'

createRecordSet(hzId)
.then(data => console.log(data))

function createRecordSet (hzId) {
  const params = {
    HostedZoneId: hzId,
    ChangeBatch: {
      Changes: [
        {
          Action: 'CREATE',
          ResourceRecordSet: {
            Name: 'hbfl.online',
            Type: 'A',
            AliasTarget: {
              DNSName: 'hamsterELB-956462610.us-east-1.elb.amazonaws.com',
              EvaluateTargetHealth: false,
              HostedZoneId: 'Z35SXDOTRQ7X7K'
            }
          }
        }
      ]
    }
  }
  // Link to ELB Regions:
  // https://docs.aws.amazon.com/general/latest/gr/elb.html

  return new Promise((resolve, reject) => {
    route53.changeResourceRecordSets(params, (err, data) => {
      if (err) reject(err)
      else resolve(data)
    })
  })
}
