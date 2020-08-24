// Imports
const AWS = require('aws-sdk')

AWS.config.update({ region: 'eu-central-1' })

// Declare local variables
const s3 = new AWS.S3()

configureS3Site('hamster-bucket-robertz-psc5')
.then(data => console.log(data))

function configureS3Site (bucketName) {
  const params = {
    Bucket: bucketName,
    WebsiteConfiguration: {
      IndexDocument: {
        Suffix: "index.html"
      }
    }
  }
  return new Promise((resolve, reject) => {
    s3.putBucketWebsite(params, (err, data) => {
      if (err) reject(err)
      else resolve(data)
    })
  })
}
