<template>
  <div>
    <div style="margin-left: 700px;margin-top:20px;font-weight: bold;font-size: large">
      <i class="iconfont icon-liebiao"></i> &nbsp;问题记录
    </div>
    <el-card class="box-card" v-for="item in question">
      <div slot="header" class="clearfix">
        <div style="display: flex" >
<!--          <h2>问题{{item.questionId}}</h2>-->

          <div style="font-size: 20px;font-weight: bold;font-family: 'Microsoft YaHei',serif;" v-for="o in 1" :key="o" class="text item">
            {{ item.content }}
          </div>
          <div style="margin-left:auto;">
            <el-button  type="primary" round @click="gotolink(item.questionId,item.content)">回复</el-button>
            <el-button  type="danger" round @click="deleteQuestion(item.questionId)">删除</el-button>
          </div>
        </div>
      </div>
    </el-card>
  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "Question" ,
  methods: {
    gotolink(value,content){

      //点击跳转至上次浏览页面
      // this.$router.go(-1)

      //指定跳转地址

      this.$router.push({name:'Message',params:{questionId:value,questionContent:content}})
    },
    loadMessage(){
      request.get("/question",).then(res => {
        this.question = res.data;
      })
    },
    deleteQuestion(id){
      request.delete("/question/" + id).then(res => {
        this.$message({
          message: "删除成功",
          type: "success"
        });
        this.loadMessage()
      })

    }

  },
  created() {
    this.loadMessage()
  },
  data() {
    return {
        question:[],


    }
  }
}
</script>

<style scoped>
@import "../assets/font/iconfont.css";
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 75%;
  margin-left: 100px;
  margin-top: 30px;
}
</style>