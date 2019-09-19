import Settings from '../settings'
import Vue from 'vue'

function compileUri (uriTemplate, options) {
  let compiled = Vue.lodash.template(uriTemplate)
  return compiled(options)
}

export default {
  ok (res) {
    return res && res.data && res.data.code === Settings.responseCode.succeed
  },

  /**
   * 登录
   */
  login (loginForm, success, exp) {
    Vue.axios({
      method: 'post',
      url: Settings.apiGateway.loginUri,
      data: loginForm
    }).then(success).catch(exp)
  },

  /**
   * 获取商务线列表
   * @returns {*[]}
   */
  getBizLines (uri, success, exp) {
    Vue.axios({
      method: 'get',
      url: uri,
      data: {}
    }).then(success).catch(exp)
  },

  /**
   * 增加商务线
   */
  addBizPipeline (data, success, exception) {
    console.debug('Action: 添加一条商务线, data: {}, backendUrl: {}', data, Settings.apiGateway.addBizLineUri)
    // Send request
    Vue.axios({
      method: 'post',
      url: Settings.apiGateway.addBizLineUri,
      data: data
    }).then(success).catch(exception)
    return true
  },

  /**
   * 删除商务线
   */
  deleteBizLine (bizId, success, exp) {
    Vue.axios({
      method: 'delete',
      url: Settings.apiGateway.deleteBizLineUri + '/' + bizId,
      data: {}
    }).then(success).catch(exp)
  },

  /**
   * 添加todo
   */
  addTodo (data, success, exp) {
    Vue.axios({
      method: 'post',
      url: Settings.apiGateway.addTodoUri,
      data: data
    }).then(success).catch(exp)
  },

  getTodos (bizId, success, exp) {
    let compiled = Vue.lodash.template(Settings.apiGateway.getTodosUri)
    const uri = compiled({bizId: bizId})
    Vue.axios({
      method: 'get',
      url: uri
    }).then(success).catch(exp)
  },

  /**
   * 删除todo
   */
  deleteTodo (tid, success, exp) {
    Vue.axios({
      method: 'delete',
      url: compileUri(Settings.apiGateway.deleteTodoUri, {tid: tid})
    }).then(success).catch(exp)
  },

  /**
   * 改变todo状态
   */
  toggleTodo (data, success, exp) {
    Vue.axios({
      method: 'put',
      url: Settings.apiGateway.toggleTodoStatusUri,
      data: data
    }).then(success).catch(exp)
  },

  /**
   * 查询进展
   */
  getBizProgress (bizId, success, exp) {
    Vue.axios({
      method: 'get',
      url: compileUri(Settings.apiGateway.queryProgressUri, {bizId: bizId})
    }).then(success).catch(exp)
  },

  /**
   * 添加进展
   */
  addBizProgress (data, success, exp) {
    Vue.axios({
      method: 'post',
      url: Settings.apiGateway.addProgressUri,
      data: data
    }).then(success).catch(exp)
  },

  /**
   * 查询负责人列表
   */
  queryInChargeUser (success, exp) {
    Vue.axios({
      method: 'get',
      url: Settings.apiGateway.queryInChargeUserUri
    }).then(success).catch(exp)
  }
}
