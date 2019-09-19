<template>
    <div>
      <div class="todo-header">
        <input class="input-july"
               autofocus autocomplete="off"
               placeholder="What needs to be done?"
               v-model="newTodo"
               @keyup.enter="addTodo">
      </div>
      <div class="todo-main">
        <ul class="todo-list">
          <li v-for="todo in todos"
              :key="todo.tid"
              :class="{completed:todo.completed}"
              class="todo-detail">
            <div class="todo-view">
              <!--<input type="checkbox" class="toggle" v-model="todo.completed">-->
              <el-checkbox v-model="todo.completed" @change="toggleTodo(todo)"></el-checkbox>
              <label @dbclick="editTodo(todo)" class="todo-content">{{todo.content}}</label>
              <i class="el-icon-close destroy" @click="removeTodo(todo)"></i>
            </div>
          </li>
        </ul>
      </div>
    </div>
</template>

<script>
import Backends from '@/services/backend'
export default {
  name: 'Todo',
  props: {
    bizId: Number
  },
  data () {
    return {
      todos: [],
      newTodo: '',
      editedTodo: null
    }
  },
  created () {
    Backends.getTodos(this.bizId, res => {
      this.todos = res.data.data
    }, res => {
      console.log(res.data)
      this.message('获取todo列表失败')
    })
  },
  methods: {
    addTodo () {
      const value = this.newTodo && this.newTodo.trim()
      if (!value) {
        return
      }
      Backends.addTodo({bizId: this.bizId, content: value}, res => {
        const tid = res.data.data.tid
        this.todos.push({
          tid: tid,
          bizId: this.bizId,
          content: value,
          completed: false
        })
        this.newTodo = ''
      }, res => {
        console.log(res.data)
        this.$message('添加todo出错')
      })
    },
    editTodo (todo) {
      console.log(todo)
    },
    removeTodo (todo) {
      console.log(todo)
      Backends.deleteTodo(todo.tid, res => {
        if (Backends.ok(res)) {
          this.todos.splice(this.todos.indexOf(todo), 1)
        }
      }, res => {
        console.log(res.data)
        this.$message('删除todo出错')
      })
    },
    toggleTodo (todo) {
      const data = {tid: todo.tid, completed: todo.completed}
      Backends.toggleTodo(data, res => {
        // if (Backends.ok(res)) {
        //   this.message('状态修改成功')
        // } else {
        //   console.log(res.data)
        // }
        console.log(res.data)
      })
    }
  },
  filters: {
    pp: function (n) {
      return 123
    }
  }
}
</script>

<style scoped lang="less">
@destroy-color: #af5b5e;

.todo-main {
  position: relative;
  z-index: 2;
  border-top: 1px solid #e6e6e6;
}
// list
.todo-list {
  margin: 0;
  padding: 0;
  list-style: none;
}
.todo-list li {
  position: relative;
  font-size: 0.8rem;
  border-bottom: 1px solid #ededed;
}

.todo-list li:last-child {
  border-bottom: none;
}

.todo-list li.editing {
  border-bottom: none;
  padding: 0;
}

.todo-list li.editing .edit {
  display: block;
  width: 506px;
  padding: 12px 16px;
  margin: 0 0 0 43px;
}

.todo-list li.editing .view {
  display: none;
}
.todo-list li .todo-content {
  word-break: break-all;
  padding: 15px 60px 15px 15px;
  display: inline-block;
  line-height: 1.2;
  transition: color 0.4s;
}

.todo-list li.completed .todo-content {
  color: #d9d9d9;
  text-decoration: line-through;
}

.todo-list li .destroy {
  display: none;
  position: absolute;
  top: 0.8rem;
  right: 5px;
  margin: auto 0;
  font-size: 20px;
  color: @destroy-color;
  transition: color 0.2s ease-out;
  cursor: pointer;
}
.todo-list li:hover .destroy {
  display: block;
}
</style>
