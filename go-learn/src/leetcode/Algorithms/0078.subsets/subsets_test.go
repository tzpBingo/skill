package Problem0078

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/assert"
)

type question struct {
	para
	ans
}

// para 是参数
type para struct {
	nums []int
}

// ans 是答案
type ans struct {
	one [][]int
}

func Test_Problem0078(t *testing.T) {
	ast := assert.New(t)

	qs := []question{

		question{
			para{
				[]int{1, 2, 3},
			},
			ans{[][]int{
				[]int{},
				[]int{1},
				[]int{2},
				[]int{1, 2},
				[]int{3},
				[]int{1, 3},
				[]int{2, 3},
				[]int{1, 2, 3},
			}},
		},

		question{
			para{
				[]int{9,0,3,5,7},
			},
			ans{[][]int{
				[]int{},
				[]int{9},
				[]int{0},
				[]int{0,9},
				[]int{3},
				[]int{3,9},
				[]int{0,3},
				[]int{0,3,9},
				[]int{5},
				[]int{5,9},
				[]int{0,5},
				[]int{0,5,9},
				[]int{3,5},
				[]int{3,5,9},
				[]int{0,3,5},
				[]int{0,3,5,9},
				[]int{7},
				[]int{7,9},
				[]int{0,7},
				[]int{0,7,9},
				[]int{3,7},
				[]int{3,7,9},
				[]int{0,3,7},
				[]int{0,3,7,9},
				[]int{5,7},
				[]int{5,7,9},
				[]int{0,5,7},
				[]int{0,5,7,9},
				[]int{3,5,7},
				[]int{3,5,7,9},
				[]int{0,3,5,7},
				[]int{0,3,5,7,9},
			}},
		},

		// 如需多个测试，可以复制上方元素。
	}

	for _, q := range qs {
		a, p := q.ans, q.para
		fmt.Printf("~~%v~~\n", p)

		ast.Equal(a.one, subsets(p.nums), "输入:%v", p)
	}
}
