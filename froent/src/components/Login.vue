<template>
    <div class="login-container">
      <div class="login-box"
           style="background-image: url('https://dn-coding-net-production-static.qbox.me/a8f69aba-9700-4c81-88e2-6aee3d36f8f9.jpg')">
        <div class="login-box-form">
          <h2>July</h2>
          <el-form :model="loginForm">
            <el-form-item>
              <el-input placeholder="请输入用户名" v-model="loginForm.username" class="input-with-select">
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-input placeholder="请输入密码" v-model="loginForm.password" type="password" class="input-with-select">
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit" style="width: 100%" v-bind:disabled="loginDisabled">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
</template>

<script>
import Backends from '../services/backend'
import { Setitem } from '../services/common'
import Settings from '../settings'

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      }
    }
  },
  computed: {
    loginDisabled: function () {
      return !(this.loginForm.username.trim() !== '' && this.loginForm.password.trim() !== '')
    }
  },
  methods: {
    onSubmit () {
      Backends.login(this.loginForm, res => {
        if (Backends.ok(res)) {
          // set token
          const token = res.data.data.token
          if (token) {
            Setitem(Settings.constant.lsTokenName, token)
            Setitem(Settings.constant.realName, res.data.data.realName)
            Setitem(Settings.constant.username, res.data.data.username)
            // router next
            const redirect = (this.$route.query && this.$route.query.redirect) || '/'
            this.$router.push(redirect)
          } else {
            this.$message('用户名或密码有误')
          }
        } else {
          this.$message(res.data.msg)
        }
      }, res => {
        if (res instanceof Error) {
          this.$message(res.message)
        } else {
          console.log(res)
          console.log(res.status)
          this.$message(res.message)
        }
      })
    }
  }
}
</script>

<style scoped lang="less">
.login-container {
  min-height: 100%;
  overflow-x: hidden;

  .login-box {
    position: relative;
    display: flex;
    -moz-box-pack: center;
    -webkit-box-pack: center;
    justify-content: center;
    -webkit-box-align: center;
    -moz-box-align: center;
    align-items: center;
    height: calc(100vh);
    background-size: cover;
    background-position: center center;
    .login-box-form {
      position: relative;
      width: 350px;
      z-index: 10;
      padding: 30px;
      -moz-border-radius: 5px;
      -webkit-border-radius: 5px;
      border-radius: 5px;
      background: rgb(255, 255, 255);
      box-shadow: 0 40px 90px 0 rgba(40,40,90,.09), 0 13px 30px 0 rgba(40,40,90,.09), 0 4px 13px 0 rgba(0,0,0,.065);

      h2 {
        text-align: center;
      }
    }
  }
}
</style>
