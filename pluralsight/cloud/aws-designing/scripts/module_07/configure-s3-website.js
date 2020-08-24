// Imports
const AWS = require('aws-sdk')

AWS.config.update({ region: 'eu-central-1' })

// Declare local variables
const s3 = new AWS.S3()

configureS3Site('/* TODO: Add your S3 bucket name */')
.then(data => console.log(data))

function configureS3Site (bucketName) {
  // TODO: Create params const object

  return new Promise((resolve, reject) => {
    // Call putBucketWebsite
  })
}
