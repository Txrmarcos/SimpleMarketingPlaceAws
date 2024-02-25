import axios from 'axios'
import { useEffect, useState } from 'react'
import styles from "../styles/Buy.module.css"
import { UUID } from 'crypto'



export default function Product() {

    const [product, setProduct] = useState<any>([])
    const [saldo, setSaldo] = useState<any>()
    const [name, setName] = useState<any>()
    const [itens, setItens] = useState<any>()

    async function findUser() {
        try {
            const email = "marcos@gmail.com"
            const user = await axios.get(`http://34.203.29.179:8081/user/${email}`)
            await cart(email)
            setSaldo(user.data.saldo)
            setName(user.data.name)


        } catch (error) {
            console.error(error)

        }

    }



    async function cart(email: String) {
        try {
            const carrinho = await axios.get(`http://34.203.29.179:8081/buy/${email}`)
            const nCarrinhos = carrinho.data
            const itens = nCarrinhos.map((item: any) => item.productId)
            const quant = nCarrinhos.map((item: any) => item.nitens)
            const produto = await findProduct(itens, quant)
            setProduct(produto)
            setItens(quant)



        } catch (error) {
            console.error(error)
            return null

        }

    }
    async function findProduct(id: any, quant: any) {
        try {
            const carrinho = id.map(async (ids: number, index: number) => {
                const response = await axios.get(`https://fakestoreapi.com/products/${ids}`);
                const data = response.data
                data.quantidade = quant[index]
                console.log(data)
                return data;
            })
            const dados = await Promise.all(carrinho)
            console.log(dados)

            return dados
        } catch (error) {
            console.error(error)
            return null

        }

    }



    useEffect(() => {
        findUser()
    }, [])

    return (
        <>
            <header className={styles.h}>
                <h1 >Obrigado pela Compra {name}!</h1>
                <div className={styles.products}>
                    {product.map((myCart: any) => (

                        <div key={myCart.id} className={styles.item}>
                            <h2>{myCart.title}</h2>
                            <img src={myCart.image} alt={myCart.title} height={250} width={200} />
                        </div>

                    ))
                    }

                </div>
            </header>
        </>
    )
}