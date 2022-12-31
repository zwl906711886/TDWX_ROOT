<template>
  <div>
    <div style="margin-top: 40px;display: flex">
      <h2 style="margin-left: 20px">选择统计对象</h2>
      <el-select v-model="value" placeholder="请选择" size="big" style="margin-left: 15px">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            @click="fun1()">
        </el-option>
      </el-select>
    </div>
    <div style="display: flex">
      <div style="padding: 10px;margin-top: 40px">
        <el-card >
          <div id="myChart" :style="{width: '600px', height: '450px'}"></div>
        </el-card>
      </div>
      <div style="margin-top: 40px;padding: 10px;margin-left: 10px">
        <el-card >
          <h3>学生数据统计柱状图</h3>
          <div id="report" style="width: 1000px;height: 425px;"></div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";
import * as echarts from "echarts";
export default {
  name: "Home",
  components :{
  },
  data() {
    return {
      charts: "",
      score: "100",
      source: [
        ["类型", "数量"],
      ],
      options: [{
        value: 'degree',
        label: '最高学历'
      }, {
        value: 'major',
        label: '报考专业'
      },{
        value: 'state',
        label: '当前状态'
      },
        {
          value: 'school',
          label: '学院'},
      ],
      value: 'degree'
    }
  },
  mounted() {
    this.drawLine()
    this.draw()
  },
  methods: {
    fun1(){
      console.log(this.value)
      this.drawLine()
      this.draw()
    },
    draw() {
      this.charts = echarts.init(document.getElementById('report'));
      if(this.value==='degree'){
        this.source=[]
        this.source.push(["类型", "数量"])
        request.get("/enroll/countDegree").then(res => {
          res.data.forEach(item => {
            let namer=item.degree;
            let countr=item.count;
            this.source.push([namer,countr])
          })
          // 绘制图表
          this.charts.setOption(option);
        })
      }
      else if(this.value==='major'){
        this.source=[]
        this.source.push(["类型", "数量"])
        request.get("/enroll/countMajor").then(res => {
          res.data.forEach(item => {
            let namer=item.major;
            let countr=item.count;
            this.source.push([namer,countr])
          })
          // 绘制图表
          this.charts.setOption(option);
        })
      }
      else if(this.value==='state'){
        this.source=[]
        this.source.push(["类型", "数量"])
        request.get("/enroll/countState").then(res => {
          res.data.forEach(item => {
            // console.log(item)
            let namer=item.state;
            let countr=item.count;
            this.source.push([namer,countr])
          })
          // 绘制图表
          this.charts.setOption(option);
        })
      }
      else if(this.value==='school'){
        this.source=[]
        this.source.push(["类型", "数量"])
        request.get("/enroll/countSchool").then(res => {
          res.data.forEach(item => {
            // console.log(item)
            let namer=item.school;
            let countr=item.count;
            this.source.push([namer,countr])
          })
          // 绘制图表
          this.charts.setOption(option);
        })
      }
      let option= {
        legend: {},
        tooltip: {},
        dataset: {
          source: this.source    //连接数据
        },
        xAxis: { type: "category" },
        yAxis: {
          name:" 人数"
        },
        grid: { bottom: 30 },
        series: [
          {
            type: "bar",    //表示这个是柱状图
            barCategoryGap: "40%",
            itemStyle: { color: "#81cebe" },   //定义颜色
            tooltip: {
              formatter: params => {
                console.log(params)
                // 打印这个params,按自己需要拼接字符串
                // console.log(params)
                return ` ${params.value[0]}
                         ${params.seriesName}:${params.value[1]}`;
              }
            }
          },
        ]
      };
    },
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$root.echarts.init(document.getElementById('myChart'))
      let option = {
        title: {
          text: '学生数据统计饼状图',
          // subtext: '虚拟数据',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '用户比例',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
      if(this.value==='degree')
      {
        option.series[0].data=[]
        request.get("/enroll/countDegree").then(res => {
          res.data.forEach(item => {
            option.series[0].data.push({name: item.degree, value: item.count})
          })
          // 绘制图表
          myChart.setOption(option);
        })
      }
      else if(this.value==='major')
      {
        option.series[0].data=[]

        request.get("/enroll/countMajor").then(res => {
          console.log(res)
          res.data.forEach(item => {
            option.series[0].data.push({name: item.major, value: item.count})
          })
          // 绘制图表
          myChart.setOption(option);
        })
        console.log("error1")
      }
      else if(this.value==='state')
      {
        option.series[0].data=[]
        request.get("/enroll/countState").then(res => {
          res.data.forEach(item => {
            option.series[0].data.push({name: item.state, value: item.count})
          })
          // 绘制图表
          myChart.setOption(option);
        })
        console.log("error3")
      }
      else if(this.value==='school')
      {
        option.series[0].data=[]
        request.get("/enroll/countSchool").then(res => {
          res.data.forEach(item => {
            option.series[0].data.push({name: item.school, value: item.count})
          })
          // 绘制图表
          myChart.setOption(option);
        })
      }
      else {
        console.log("error")
      }
    }
  },


}
</script>

<style scoped>

</style>


