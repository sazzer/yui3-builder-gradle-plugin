var ToDoList = Y.Base.create("ToDoList",
        Y.ModelList,
        [], {
            model: Y.ToDoList.ToDoEntry
        }, {
            ATTRS: {
            }
        });

Y.namespace("ToDoList").ToDoList = ToDoList;
