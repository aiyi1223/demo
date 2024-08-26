<template>
  <div class="login-container">
    <el-form :model="user" @submit.native.prevent="handleLogin">
      <el-form-item label="用户名">
        <el-input v-model="user.name" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="user.password" type="password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleLogin">登录</el-button>
        <el-button @click="goToRegister" style="margin-left: 10px;">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      user: {
        name: '',
        password: '',
      },
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('http://localhost:8084/User/login', this.user);
        const data = response.data;

        // 根据返回的状态码和信息进行提示
        if (data.code === 1) {
          this.$message.success(`登录成功`);
          this.$router.push('/success');
        } else {
          this.$message.error(`登录失败: ${data.msg}`);
          this.$router.push('/failure');
        }
      } catch (error) {
        console.error('登录错误:', error);
        this.$message.error('登录出错，请稍后再试');
        this.$router.push('/failure');
      }
    },
    goToRegister() {
      this.$router.push('/register');
    },
  },
};
</script>

<style scoped>
.login-container {
  width: 300px;
  margin: 100px auto;
}
</style>
