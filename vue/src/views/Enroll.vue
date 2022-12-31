<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary" @click="export2Excel">导出</el-button>
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0;display: flex">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load(value)">查询</el-button>
      <el-button type="primary" icon="el-icon-refresh" @click="load0"></el-button>
      <el-select  style="margin-left: auto"  v-model="value" placeholder="请选择批次">
        <el-option
            @click="load(value)"
            v-for="item in nums"
            :key="item"
            :label="item"
            :value="item">
        </el-option>
      </el-select>
    </div>
    <el-table
        v-loading="loading"
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          width="195px"
          prop="name"
          label="姓名"
          sortable
      >
      </el-table-column>
      <el-table-column
          width="295px"
          prop="school"
          label="报考学院">
      </el-table-column>
      <el-table-column
          prop="major"
          width="295px"
          label="报考专业">
      </el-table-column>
      <el-table-column
          prop="degree"
          width="155px"
          label="最高学历">
      </el-table-column>
      <el-table-column
          prop="batch"
          width="295px"
          label="报名批次">
      </el-table-column>
      <el-table-column
            prop="state"
            width="155px"
            label="当前状态"
            :filters="[{ text: '未录取', value: '未录取' },{ text: '已录取', value: '已录取' },{text: '审核中',value: '审核中'}]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
          <template #default="scope">
            <el-tag
                :type="scope.row.state === '已录取' ? 'success' : 'primary'"
                disable-transitions>{{scope.row.state}}</el-tag>
          </template>
      </el-table-column>
      <el-table-column
          width="295px"
          label="操作" >
        <template #default="scope">
          <el-button size="mini" @click="details(scope.row)">预览报名表</el-button>
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="mini" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="提示" v-model="dialogVisible" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item label="姓名">
          <el-input v-model="form.name" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="报考学院">
          <el-select v-model="form.school" placeholder="请选择">
            <el-option
                v-for="item in options1"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="报考专业">
          <el-select v-model="form.major" placeholder="请选择">
            <el-option
                v-for="item in options2"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="最高学历">
          <el-select v-model="form.degree" placeholder="请选择">
            <el-option
                v-for="item in options3"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="当前状态">
          <el-select v-model="form.state" placeholder="请选择">
            <el-option
                v-for="item in options4"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="批次">
          <el-select v-model="form.batch" placeholder="请选择">
            <el-option
                v-for="item in nums"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
      </template>
    </el-dialog>
    <el-dialog title="报名表预览" v-model="vis" width="50%">
      <el-card>
<!--        <el-form :model="detail">-->
<!--          <el-descriptions model="detail" class="margin-top" title="报名表" :column="4" direction="vertical">-->
<!--            <el-descriptions-item label="姓名">{{detail.name}}</el-descriptions-item>-->
<!--            <el-descriptions-item label="报考学院">{{detail.school}}</el-descriptions-item>-->
<!--            <el-descriptions-item label="报考专业" :span="2">{{detail.major}}</el-descriptions-item>-->
<!--            <el-descriptions-item label="最高学历">-->
<!--              <el-tag size="small">{{detail.degree}}</el-tag>-->
<!--            </el-descriptions-item>-->
<!--            <el-descriptions-item label="当前状态">-->
<!--              <el-tag size="small">{{detail.state}}</el-tag>-->
<!--            </el-descriptions-item>-->
<!--          </el-descriptions>-->
<!--        </el-form>-->
        <table :model="detail">
          <tr>
            <td width="114">姓名</td>
            <td width="118">{{detail.name}}</td>
            <td width="132">报考院系</td>
            <td width="128">{{detail.school}}</td>
            <td width="184" rowspan="5">照片</td>
          </tr>
          <tr>
            <td>性别</td>
            <td>男</td>
            <td>报考专业</td>
            <td>{{detail.major}}</td>
          </tr>
          <tr>
            <td>出生日期</td>
            <td>1996.7.23</td>
            <td>学历</td>
            <td>{{detail.degree}}</td>
          </tr>
          <tr>
            <td>政治面貌</td>
            <td>预备党员</td>
            <td>外语水平</td>
            <td>英语专业四级</td>
          </tr>
          <tr>
            <td>民族</td>
            <td>汉族</td>
            <td>计算机水平</td>
            <td>ccf通过</td>
          </tr>
          <tr>
            <td>通讯地址</td>
            <td colspan="4">湖南省长沙市岳麓区天马小区2栋</td>
          </tr>
          <tr>
            <td>联系方式</td>
            <td colspan="2">12345678901</td>
            <td>邮编</td>
            <td>054001</td>
          </tr>
          <tr>
            <td>备注</td>
            <td colspan="4">无</td>
          </tr>
          <tr>
            <td><p>个</p>
              <p>人</p>
              <p>经</p>
              <p>历</p>
            </td>
            <td colspan="4">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="5">
              <div style="text-align: left">
                (签字前请认真核对以上内容）
              </div>
              <div>
                &nbsp;
              </div>
              <div>
                <h2>诚信承诺书</h2>
              </div>
              <div>
                &nbsp;
              </div>
              <div style="font-size: smaller">
                我保证提供的上述信息真实有效，并愿意承担由于信息虚假带来的一切后果
              </div>
              <div>
                &nbsp;
              </div>
              <div style="font-size: smaller;text-align: right">
                签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </div>
              <div>
                &nbsp;
              </div>
              <div style="font-size: smaller;text-align: right">
                年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </div>
            </td>
          </tr>
          <tr>
            <td colspan="5">
              <div style="text-align: left">
                招生单位意见
              </div>
              <div>
                &nbsp;
              </div>
              <div>
                <h2>同意报考</h2>
              </div>
              <div>
                &nbsp;
              </div>
              <div style="font-size: smaller;text-align: right">
                （公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </div>
              <div>
                &nbsp;
              </div>
              <div style="font-size: smaller;text-align: right">
                年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </div>
            </td>
          </tr>

        </table>
      </el-card>

    </el-dialog>
  </div>
</template>

<script>
import E from 'wangeditor'
import request from "@/utils/request";
let editor;

export default {
  name: "Enroll",
  components: {},
  data() {
    return {
      value:"全部批次",
      nums:['全部批次'],
      options1: [{
        value: '信息科学与工程学院',
        label: '信息科学与工程学院'
      }, {
        value: '工商管理学院',
        label: '工商管理学院'
      },
        {
          value: '材料科学与工程学院',
          label: '材料科学与工程学院'
        }],

      options2:[{
        value: '软件工程',
        label: '软件工程'
      },{
        value: '计算机科学与技术',
        label: '计算机科学与技术'
      },{
        value: '通信工程',
        label: '通信工程'
      },{
        value: '会计',
        label: '会计'
      },{
        value: '公共管理',
        label: '公共管理',
      },{
        value: '财务管理金融工程',
        label: '财务管理金融工程',
      },{
        value: '材料科学',
        label: '材料科学',
      }],

      options3:[{
        value: '小学',
        label: '小学'
      },{
        value: '初中',
        label: '初中'
      },{
        value: '高中',
        label: '高中'
      },{
        value: '专科',
        label: '专科'
      },
        {
          value: '本科及以上',
          label: '本科及以上'
        }
      ],
      options4:[{
        value: '审核中',
        label: '审核中'
      },{
        value: '已录取',
        label: '已录取'
      },{
        value: '未录取',
        label: '未录取'
      }],
      loading: true,
      form: {},
      dialogVisible: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [{
        name:'',
        school:'',
        major:'',
        degree:'',
        state:''
      }],
      vis: false,
      detail: {},
    }
  },
  created() {
    this.load(this.value)

  },
  methods: {
    filterTag(value, row) {
      return row.state === value;
    },
    // 导出
    export2Excel() {
      require.ensure([], () => {
        const { export_json_to_excel } = require('../excel/Export2Excel');
        const tHeader = ['姓名', '报考学院','报考专业','最高学历','当前状态'];
        // 上面设置Excel的表格第一行的标题
        const filterVal = ['name', 'school','major','degree','state'];
        // 上面的 goodsName、goodsId、是tableData里对象的属性
        const list = this.tableData;  //把data里的tableData存到list
        const data = this.formatJson(filterVal, list);
        export_json_to_excel(tHeader, data, '报名管理表');
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },

    details(row) {
      this.detail = row
      this.vis = true
    },
    filesUploadSuccess(res) {
      console.log(res)
      this.form.cover = res.data
    },
    load0(){
      this.search=''
      this.loading = true
      this.value='全部批次'
      request.get("/enroll", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.loading = false

        this.tableData = res.data.records
        this.total = res.data.total

      })
    },
    load(value) {
      if(value==='全部批次'){
        this.loading = true
        request.get("/enroll", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            search: this.search
          }
        }).then(res => {
          // console.log(res)
          this.loading = false

          this.tableData = res.data.records
          this.total = res.data.total

        })
        request.get("/enroll/findBatch").then(res=>{
          // console.log(res)
          let BatchList=res.data

          for (let i = 1; i < BatchList.length; i++) {

            this.nums[i]=BatchList[i].batchName;
          }

        })
        // console.log("123")
        console.log(this.nums)
        console.log(this.tableData)
      }
      else {
        this.loading = true
        request.get("/enroll/findall", {
          params: {
            pageNum: this.currentPage,
            pageSize: this.pageSize,
            search: this.search,
            choice: this.value
          }
        }).then(res => {
          console.log(res)
          console.log(this.value)
          this.tableData=[]
          this.loading = false
          for(var i=0;i<res.data.records.length;i++) {
              this.tableData.push(res.data.records[i])
          }
          console.log(this.tableData)
          this.total=res.data.total
        })
      }
    },

    add() {
      this.dialogVisible = true
      this.form = {}

      this.$nextTick(() => {
        // 关联弹窗里面的div，new一个 editor对象
        if (!editor) {
          editor = new E('#div1')

          // 配置 server 接口地址
          editor.config.uploadImgServer = 'http://' + window.server.filesUploadUrl + ':9090/files/editor/upload'
          editor.config.uploadFileName = "file"  // 设置上传参数名称
          editor.create()
        }

        editor.txt.html("")

      })

    },
    save() {
      if (this.form.id) {  // 更新
        console.log("12")
        request.put("/enroll", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load(this.value) // 刷新表格的数据
          this.dialogVisible = false  // 关闭弹窗
        })
      } else {  // 新增
        console.log("11")
        // let userStr = sessionStorage.getItem("user") || "{}"
        // let user = JSON.parse(userStr)
        // this.form.author = user.nickName

        request.post("/enroll", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }

          this.load(this.value) // 刷新表格的数据
          this.dialogVisible = false  // 关闭弹窗
        })
      }

    },
    handleEdit(row) {
      console.log("nmd")
      console.log(row)
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
      // this.form = JSON.parse(JSON.stringify(row))
      // this.dialogVisible = true
      //
      // this.$nextTick(() => {
      //   // 关联弹窗里面的div，new一个 editor对象
      //   // 关联弹窗里面的div，new一个 editor对象
      //   if (!editor) {
      //     editor = new E('#div1')
      //
      //     // 配置 server 接口地址
      //     editor.config.uploadImgServer = 'http://localhost:9090/files/editor/upload'
      //     editor.config.uploadFileName = "file"  // 设置上传参数名称
      //     editor.create()
      //   }
      //
      //   editor.txt.html(row.content)
      // })
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/enroll/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load(this.value)  // 删除之后重新加载表格的数据
      })
    },
    handleSizeChange(pageSize) {   // 改变当前每页的个数触发
      this.pageSize = pageSize
      this.load(this.value)
    },
    handleCurrentChange(pageNum) {  // 改变当前页码触发
      this.currentPage = pageNum
      this.load(this.value)
    }
  }
}

</script>

<style scoped>
@import "../assets/font/iconfont.css";

table {
  width: 700px;margin: auto;border: 1px solid #000000;border-collapse: collapse
}
table td {
  border: 1px solid #000000;font: 1.1em/1.5 arial,microsoftyahei,sans-serif;padding: 6px;text-align: center;
}

</style>