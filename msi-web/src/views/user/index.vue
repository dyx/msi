<template>
  <el-form
    ref="searchFormRef"
    label-width="60px"
    label-position="left"
    inline
    :model="searchForm"
  >
    <el-form-item prop="username" label="用户名">
      <el-input v-model="searchForm.username" placeholder='用户名精确查询' />
    </el-form-item>
    <el-form-item prop="nickname" label="昵称">
      <el-input v-model="searchForm.nickname" placeholder='昵称模糊查询' />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="search">搜索</el-button>
      <el-button @click="reset">重置</el-button>
    </el-form-item>
  </el-form>
  <el-row style="margin-bottom: 18px">
    <el-col :span="12"> </el-col>
    <el-col :span="12">
      <el-button type="success" style="float: right" @click="showAdd"
        >新增</el-button
      >
    </el-col>
  </el-row>
  <el-table
    border
    header-align="center"
    :data="tableData.records"
    v-loading="tableLoading"
  >
    <el-table-column
      header-align="center"
      type="index"
      label="序号"
      width="60"
    ></el-table-column>
    <el-table-column
      header-align="center"
      prop="username"
      label="用户名"
      width="120"
    ></el-table-column>
    <el-table-column
      header-align="center"
      prop="nickname"
      label="昵称"
      width="150"
    ></el-table-column>
    <el-table-column
      header-align="center"
      prop="createTime"
      label="创建时间"
      width="180"
    ></el-table-column>
    <el-table-column label="操作" align="center" fixed="right" width="200">
      <template #default="scope">
        <el-button type="primary" size="small" @click="showView(scope.row.id)"
          >查看</el-button
        >
        <el-button type="primary" size="small" @click="showUpdate(scope.row.id)"
          >修改</el-button
        >
        <el-popconfirm
          title="确定要删除这条记录吗？"
          confirm-button-text="确定"
          cancel-button-text="取消"
          @confirm="remove(scope.row.id)"
        >
          <template #reference>
            <el-button type="danger" size="small">删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
    <template #empty>
      <el-empty>
        <template #description>
          暂未查询到数据
        </template>
      </el-empty>
    </template>
  </el-table>
  <el-pagination
    style="margin-top: 10px"
    v-model:currentPage="current"
    v-model:pageSize="size"
    :page-sizes="pageSizes"
    layout="total, sizes, prev, pager, next"
    :total="tableData.total"
    @current-change="search"
    @size-change="search"
  />
  <view-user
    :visible="viewVisible"
    :id="id"
    @close="viewVisible = false"
  ></view-user>
  <add-user
    :visible="addVisible"
    @closed="closeAdd"
    @success="addSuccess"
  ></add-user>
  <update-user
    :visible="updateVisible"
    :id="id"
    @closed="closeUpdate"
    @success="updateSuccess"
  ></update-user>
</template>

<script>
import { reactive, toRefs, ref, onMounted } from 'vue'
import ViewUser from './viewUser.vue'
import AddUser from './addUser.vue'
import UpdateUser from './updateUser.vue'
import { getUserPage, removeUser } from '@/api/user'

const PAGE_SIZES = [10, 20, 30, 50]
export default {
  components: { ViewUser, AddUser, UpdateUser },
  setup() {
    const searchFormRef = ref()
    const state = reactive({
      viewVisible: false,
      addVisible: false,
      updateVisible: false,
      tableLoading: false,
      id: 0,
      searchForm: {
        username: ''
      },
      pageSizes: PAGE_SIZES,
      current: 1,
      size: PAGE_SIZES[0],
      tableData: {
        total: 0,
        records: []
      }
    })

    const search = () => {
      state.tableLoading = true
      state.searchForm.current = state.current
      state.searchForm.size = state.size
      getUserPage(state.searchForm)
        .then(res => {
          state.tableData = res.data
        })
        .finally(() => (state.tableLoading = false))
    }

    const reset = () => {
      searchFormRef.value.resetFields()
      search()
    }

    const showView = id => {
      state.id = id
      state.viewVisible = true
    }

    const showAdd = () => {
      state.addVisible = true
    }
    const closeAdd = () => {
      state.addVisible = false
    }
    const addSuccess = () => {
      search()
    }

    const showUpdate = id => {
      state.id = id
      state.updateVisible = true
    }
    const closeUpdate = () => {
      state.updateVisible = false
    }
    const updateSuccess = () => {
      search()
    }

    const remove = id => {
      removeUser(id).then(() => {
        search()
      })
    }

    onMounted(() => {
      search()
    })

    return {
      searchFormRef,
      ...toRefs(state),
      search,
      reset,
      showView,
      showAdd,
      closeAdd,
      addSuccess,
      showUpdate,
      closeUpdate,
      updateSuccess,
      remove
    }
  }
}
</script>

<style scoped>
:deep(.el-empty__description) {
  height: 60px;
  margin: 0;
  font-weight: bold;
}

:deep(.el-empty) {
  padding: 0;
}

:deep(.el-link) {
  vertical-align: inherit;
}
</style>
