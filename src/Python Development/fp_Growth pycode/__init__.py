#coding=utf-8
import tree_builder
import tree_miner

routines = [    
           ['Cola','Egg','Ham'],
           ['Cola','Diaper','Beer'],
           ['Cola','Beer','Diaper','Ham'],
           ['Diaper','Beer']
        ]                                 #事务数据集
min_sup = 2                             #最小支持度计数
headerTable = {}        #头结点表，用来存放各个项的索引

treeBuilder = tree_builder.Tree_builder(routines=routines, min_sup=min_sup, headerTable=headerTable)    #建造FP_Tree
tree_miner.Tree_miner(Tree=treeBuilder.tree, min_sup=min_sup, headerTable=headerTable)         #对FP_Tree进行频繁项集的挖掘