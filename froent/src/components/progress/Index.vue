<template>
    <div>
      <div class="progress-header">
        <input class="input-july"
               autofocus autocomplete="off"
               placeholder="New progress?"
               v-model="newProgress"
               @keyup.enter="addProgress">
      </div>
      <div>
        <timeline>
          <!-- <timeline-title>2018-08-11 12:12:23</timeline-title> -->
          <timeline-item v-for="(item, index) in bizProgresses"
                         v-bind:key="item"
                         :bg-color="timeLineColor(index)"
                         :hollow="hollow(index)">
            {{item.createdOn}} {{ item.content }}
          </timeline-item>
        </timeline>
      </div>
    </div>
</template>

<script>
import {Timeline, TimelineItem, TimelineTitle} from 'vue-cute-timeline'
import Backends from '@/services/backend'
import {DateFormat} from '@/services/dateExt'
export default {
  name: 'biz-progress',
  components: {
    Timeline,
    TimelineItem,
    TimelineTitle
  },
  data () {
    return {
      newProgress: '',
      bizProgresses: []
    }
  },
  props: {
    bizId: Number
  },
  created () {
    Backends.getBizProgress(this.bizId, res => {
      if (Backends.ok(res)) {
        this.bizProgresses = res.data.data
      }
    }, res => {
      this.message('获取进展数据失败')
    })
  },
  methods: {
    hollow (index) {
      return index % 2 === 0
    },
    timeLineColor (index) {
      return this.hollow(index) ? '' : '#9dd8e0'
    },
    addProgress () {
      let newItem = {
        bizId: this.bizId,
        createdOn: DateFormat(new Date(), 'yyyy-MM-dd hh:mm'),
        content: this.newProgress
      }
      Backends.addBizProgress(newItem, res => {
        if (Backends.ok(res)) {
          this.bizProgresses.unshift(newItem)
          this.newProgress = ''
        }
      }, res => {
        this.message('添加进展失败')
      })
    }
  }
}
</script>

<style scoped lang="less">
</style>
