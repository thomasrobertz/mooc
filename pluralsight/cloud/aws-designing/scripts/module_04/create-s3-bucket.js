// Imports
const AWS = require('aws-sdk')

AWS.config.update({ region: 'eu-central-1' })

// Declare local variables
const s3 = new AWS.S3()

createBucket('hamster-bucket-robertz-psc5')
.then((data) => console.log(data))

function createBucket (bucketName) {
  const params = {
    Bucket: bucketName,
    ACL: "public-read"
  }

  return new Promise((resolve, reject) => {
    s3.createBucket(params, (err, data) => {
      if (err) reject(err)
      else resolve(data)
    })
  })
}
