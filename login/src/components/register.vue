<template>
    <div class="register">
      <h2>注册</h2>
      <el-form :model="form" @submit.native.prevent="onSubmit">
        <el-form-item label="用户名">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="form.password" />
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input type="password" v-model="form.confirmPassword" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>
  
  <script>
  import axios from 'axios'; // 确保正确导入 axios
  
  export default {
    data() {
      return {
        form: {
          name: '', // 与后端的 User 对象字段匹配
          password: '',
          confirmPassword: '',
        },
      };
    },
    methods: {
      register() {
        if (this.form.password !== this.form.confirmPassword) {
          this.$message.error('密码不一致');
          return;
        }
  
        axios
          .post('http://localhost:8084/User/register', this.form)
          .then((response) => {
            const data = response.data;
            if (data.code === 1) {  // 假设成功的状态码是 1，R.success 时返回的状态码
              this.$message.success('注册成功');
              this.$router.push('/login');
            } else {
              this.$message.error(`注册失败: ${data.msg}`);
            }
          })
          .catch((error) => {
            this.$message.error(`注册失败: ${error.response.data.msg || '未知错误'}`);
          });
      },
    },
  };
  </script>
  
  <style scoped>
  .register {
    width: 400px;
    margin: 100px auto;
    padding: 20px;
    border: 1px solid #dcdcdc;
    border-radius: 8px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  }
  
  h2 {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .el-form-item {
    margin-bottom: 20px;
  }
  </style>
  