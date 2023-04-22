const addCartItem = (productId) => {
    const credentials = localStorage.getItem('credentials');
    if (!credentials) {
        alert('사용자 정보가 없습니다.');
        window.location.href = '/settings';
        return;
    }

    axios.request({
        method: 'post',
        url: 'member/cart',
        data: {
            productId: productId
        },
        headers: {
            'Authorization': `Basic ${credentials}`
        }
    }).then((response) => {
        if(response.status == 200) {
            alert('장바구니에 담았습니다.');
        }
    }).catch((error) => {
        alert('장바구니에 담을 수 없습니다.');
        console.error(error);
    });
}

const removeCartItem = (id) => {
    const credentials = localStorage.getItem('credentials');
    if (!credentials) {
        alert('사용자 정보가 없습니다.');
        window.location.href = '/settings';
        return;
    }

    axios.request({
        method: 'delete',
        url: '/member/cart/' + id,
        headers: {
            'Authorization': `Basic ${credentials}`
        }
    }).then((response) => {
        window.location.reload();
    }).catch((error) => {
        alert('장바구니에서 제거 할 수 없습니다.');
        console.error(error);
    });
}
