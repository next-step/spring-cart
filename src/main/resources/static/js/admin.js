const modal = document.getElementById('modal');

const showAddModal = () => {
    modal.dataset.formType = 'add';
    modal.style.display = 'block';
};

const showEditModal = (product) => {
    const elements = modal.getElementsByTagName('input');
    for (const element of elements) {
        element.value = product[element.getAttribute('name')];
    }
    modal.dataset.formType = 'edit';
    modal.dataset.productId = product.id;
    modal.style.display = 'block';
};

const hideAddModal = () => {
    modal.style.display = 'none';
    const elements = modal.getElementsByTagName('input');
    for (const element of elements) {
        element.value = '';
    }
}

const form = document.getElementById('form');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    let product = {};
    for (const entry of formData.entries()) {
        const [key, value] = entry;
        product[key] = value;
    }

    if (modal.dataset.formType === 'edit') {
        product['id'] = modal.dataset.productId;
        updateProduct(product);
        return;
    }

    createProduct(product);
});

// TODO: [1단계] 상품 관리 CRUD API에 맞게 변경
const createProduct = (product) => {
    axios.request({//TODO 각 axios.post, put, delete로 변경하면 데이터가 안담기는거 다시 확인하기
        url: '/admin/create-product',
        method: 'post',
        data: product
    }).then((response) => {
        window.location.reload();
    }).catch((error) => {
        window.alert(error.response.data.message);
    });
};

// TODO: [1단계] 상품 관리 CRUD API에 맞게 변경
const updateProduct = (product) => {
    const { id } = product;

    axios.request({//TODO 각 axios.post, put, delete로 변경하면 데이터가 안담기는거 다시 확인하기
        url: `/admin/update-product/${id}`,
        method: 'put',
        data: product
    }).then((response) => {
        window.location.reload();
    }).catch((error) => {
        window.alert(error.response.data.message);
    });
};

// TODO: [1단계] 상품 관리 CRUD API에 맞게 변경
const deleteProduct = (id) => {
    axios.request({ //TODO 각 axios.post, put, delete로 변경하면 데이터가 안담기는거 다시 확인하기
        url: `/admin/delete-product/${id}`,
        method: 'delete'
    }).then((response) => {
        window.location.reload();
    }).catch((error) => {
        window.alert(error.response.data.message);
    });
};
