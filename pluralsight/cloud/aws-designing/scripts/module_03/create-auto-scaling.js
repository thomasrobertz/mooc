// Imports
const AWS = require('aws-sdk')

AWS.config.update({ region: 'eu-central-1' })

// Declare local variables
const autoScaling = new AWS.AutoScaling()
const asgName = 'hamsterASG'
const lcName = 'hamsterLC'
const policyName = 'hamsterPolicy'
const tgArn = 'arn:aws:elasticloadbalancing:eu-central-1:425906221170:targetgroup/hamsterTG/6ae029ee8a4d1fe9'

createAutoScalingGroup(asgName, lcName)
.then(() => createASGPolicy(asgName, policyName))
.then((data) => console.log(data))

function createAutoScalingGroup (asgName, lcName) {
  const params = {
    AutoScalingGroupName: asgName,
    AvailabilityZones: [
      "eu-central-1a",
      "eu-central-1b"
    ],
    TargetGroupARNs: [
      tgArn
    ],
    LaunchConfigurationName: lcName,
    MaxSize: 2,
    MinSize: 1
  }

  return new Promise((resolve, reject) => {
    autoScaling.createAutoScalingGroup(params, (err, data) => {
      if (err) reject(err)
      else resolve(data)
    })
  }).catch(error => { console.log('caught: ', error.message); });
}

function createASGPolicy (asgName, policyName) {
  const params = {
    AdjustmentType: "ChangeInCapacity",
    AutoScalingGroupName: asgName,
    PolicyName: policyName,
    PolicyType: "TargetTrackingScaling",
    TargetTrackingConfiguration: {
      TargetValue: 5,
      PredefinedMetricSpecification: {
        PredefinedMetricType: "ASGAverageCPUUtilization"
      }
    }
  }

  return new Promise((resolve, reject) => {
    autoScaling.putScalingPolicy(params, (err, data) => {
      if(err) reject(err)
      else resolve(data)
    })
  }).catch(error => { console.log('caught: ', error)})
}
