
function init() {
    let editorModal = document.getElementById('editorModal')
    editorModal.addEventListener('show.bs.modal', event => {
        let button = event.relatedTarget
        let editType = button.getAttribute('data-bs-edit-type')
        document.getElementById('editType').value = editType
        clearEditor()

        if (editType === 'edit') {
            let todoId = button.getAttribute('data-bs-todo-id')
            setTodoToEditor(todoId)
            setDeleteButtonVisibility(true)
        } else { // new
            setDeleteButtonVisibility(false)
        }
    })

    let saveButton = document.getElementById('saveButton')
    saveButton.addEventListener('click', event => {
        let editType = document.getElementById('editType').value
        if (editType === 'new') {
            createTodo()
        } else {
            updateTodo()
        }
    })

    let deleteButton = document.getElementById('deleteButton')
    deleteButton.addEventListener('click', event => {
        deleteTodo()
    })
}

function setDeleteButtonVisibility(visible) {
    let deleteButton = document.getElementById('deleteButton')
    let visibility = (visible) ? 'visible' : 'hidden'
    deleteButton.style.visibility = visibility
}

async function setTodoToEditor(id) {
    return await get(`/api/${id}`)
        .then(response => {
            if (!response.ok) {
                return
            }
            response.json().then(todo => {
                document.querySelector('#id').value = todo.id
                document.querySelector('#title').value = todo.title
                document.querySelector('#content').value = todo.content
                document.querySelector('#dueDate').value = todo.dueDate
                document.querySelector('#done').checked = todo.done
            })
        })
}

function clearEditor() {
    document.querySelector('#id').value = null
    document.querySelector('#title').value = null
    document.querySelector('#content').value = null
    document.querySelector('#dueDate').value = null
    document.querySelector('#done').value = null
}

function getTodoFromEditor() {
    return {
        id: document.querySelector('#id').value,
        title: document.querySelector('#title').value,
        content: document.querySelector('#content').value,
        dueDate: document.querySelector('#dueDate').value,
        done: document.querySelector('#done').checked
    }
}

function createTodo() {
    let todo = getTodoFromEditor()
    post('/api/create', todo)
    .then(response => {
        console.log(response)
        if (response.ok) {
            location.href = '/'
        }
    })
}

function updateTodo() {
    let todo = getTodoFromEditor()
    post('/api/update', todo)
   .then(response => {
        console.log(response)
        if (response.ok) {
            location.href = '/'
        }
    })
}

function deleteTodo() {
    let todo = getTodoFromEditor()
    post(`/api/delete/${todo.id}`)
   .then(response => {
        console.log(response)
        if (response.ok) {
            location.href = '/'
        }
    })
}

async function get(url) {
    return await fetch(url, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    })
}

async function post(url, param) {
    return await fetch(url, {
        method: 'POST',
        body: JSON.stringify(param),
        headers: { 'Content-Type': 'application/json' }
    })
}

init()
