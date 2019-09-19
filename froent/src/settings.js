
const env = process.env.NODE_ENV
let domain = 'http://localhost:7099'
if (env === 'production') {
  domain = 'https://july-api.chaintech.info'
}

const settings = {
  domain: {
    domain: domain
  },
  apiGateway: {
    loginUri: '/api/auth/login',
    addBizLineUri: '/api/v1/pipeline/info',
    getBizLinesUri: '/api/v1/pipeline/list',
    deleteBizLineUri: '/api/v1/pipeline/info',
    addTodoUri: '/api/v1/todo',
    /* eslint-disable */
    getTodosUri: '/api/v1/${ bizId }/todo',
    /* eslint-disable */
    deleteTodoUri: '/api/v1/todo/${tid}',
    toggleTodoStatusUri: '/api/v1/todo',
    queryProgressUri: '/api/v1/progress/${bizId}',
    addProgressUri: '/api/v1/progress',
    queryInChargeUserUri: '/api/v1/user/inCharge/list'
  },
  responseCode: {
    succeed: 0
  },
  constant: {
    lsTokenName: 'TOKEN_ID',
    realName: 'REALNAME',
    username: 'USERNAME'
  }
}

export default settings
