<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex;">
    <img src="../assets/logo.png" style="margin-left: 30px" alt="">
    <img src="" alt="">
    <div style="flex: 1"></div>
    <div style="width: 100px">
      <el-dropdown>
        <span class="el-dropdown-link">
          <el-avatar :size="30" :src="user.avatar" style="position: relative; top: 10px"></el-avatar>
           {{ user.nickName }}
          <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
    export default {
      name: "Header",
      props: ['user'],
      data() {
        return {
        }
      },
      created() {
      }
    }
</script>

<style scoped>

</style>