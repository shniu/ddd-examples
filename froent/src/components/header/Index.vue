<template>
    <div>
      <el-header class="july-header">
        <div class="wrap clearfix">
          <div class="title">
            <a href="#">July</a>
          </div>
          <div class="panel">
            <!-- <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
              <el-menu-item index="1">首页</el-menu-item>
              <el-menu-item index="2">登录</el-menu-item>
            </el-menu> -->
            <span class="realname">您好，{{ realName }}</span>
            <span class="realname">
              <a href="">用户设置</a>
            </span>
            <span class="logout">
              <a href="#" @click.prevent="logout">退出</a>
            </span>
          </div>
        </div>
      </el-header>
    </div>
</template>

<script>
import { Getitem, Removeitem } from '@/services/common'
import Settings from '@/settings'
export default {
  name: 'july-header',
  data () {
    return {
      realName: Getitem(Settings.constant.realName)
    }
  },
  methods: {
    logout () {
      Removeitem(Settings.constant.lsTokenName)
      Removeitem(Settings.constant.realName)
      Removeitem(Settings.constant.username)
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped lang="less">
  @import "~@/assets/less/variables";
  .july-header {
    width: 100%;
    background-color: #fff;
    -moz-box-shadow: 0 2px 4px -1px rgba(0,0,0,0.25);
    -webkit-box-shadow: 0 2px 4px -1px rgba(0,0,0,0.25);
    -o-box-shadow: 0 2px 4px -1px rgba(0,0,0,0.25);
    box-shadow: 0 2px 4px -1px rgba(0,0,0,0.25);
    /*position: fixed;*/
    padding: 0;

    .title {
      float: left;
      a {
        display: inline-block;
        margin-top: 10px;
        font-size: 28px;
        text-decoration: none;
        color: @logo-color;
      }
    }
    .panel {
      float: right;

      span {
        display: inline-block;
        padding: 1rem 0 0 1rem;
      }
    }
  }
</style>
