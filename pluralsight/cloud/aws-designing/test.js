const { publish } = require('./lib/data/lib/sns')

async function start () {
  publish('All races complete')
  console.log('Published All races complete event')
}

start()