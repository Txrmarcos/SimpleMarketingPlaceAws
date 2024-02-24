import axios from 'axios'
import { useEffect, useState } from 'react'
import Link from "next/link"
import styles from "../styles/Product.module.css"
import { UUID } from 'crypto'
import { List } from 'postcss/lib/list'



export default function Product() {

    const [product, setProduct] = useState<any>([])
    const [saldo, setSaldo] = useState<any>()
    const [name, setName] = useState<any>()
    const [myId, setMyId] = useState<any>([])
    const [total, setTotal] = useState<number>()

    async function findUser(email: String, debito: number) {
        const email_ = email
        try {
            const user = await axios.get(`http://localhost:8081/user/${email}`)
            const userId = user.data.id;
            await cart(userId, email)
            setSaldo(user.data.saldo)
            setName(user.data.name)
            console.log("tinha que entrar aqui")


        } catch (error) {
            console.error(error)

        }

    }
    async function cart(uuid: UUID, email: String) {
        const email_ = email

        try {
            const carrinho = await axios.get(`http://localhost:8081/cart/${uuid}`)

            setMyId(uuid)
            const nCarrinhos = carrinho.data
            const itens = nCarrinhos.map((item: any) => item.productId)
            const quant = nCarrinhos.map((item: any) => item.nitens)
            const produto = await findProduct(itens, quant, email_)
            setProduct(produto)

        } catch (error) {
            console.error(error)
            return null

        }

    }
    async function findProduct(id: any, quant: any, email_: String) {
        try {
            const carrinho = id.map(async (ids: number, index: number) => {
                const response = await axios.get(`https://fakestoreapi.com/products/${ids}`);
                const data = response.data
                data.quantidade = quant[index]
                postBuy(ids, data.quantidade, email_, data.price)
                console.log(data)
                return data;

            })

            const dados = await Promise.all(carrinho)
            console.log("olha a dataaaa logo a baixo -->")
            console.log(dados)

            return dados
        } catch (error) {
            console.error(error)
            return null

        }

    }


    


    async function newWallet(): Promise<any> {
        try {
            console.log("entrou no new wallert")
            const debito = await axios.get(`http://localhost:8081/carts`)
            const data = debito.data
            const allValues = data.map((sum: any) => { console.log(sum.price); return sum.price; })
            const sum = allValues.reduce((acc:any, curr:any) => acc + curr, 0)

            console.log(sum)
            console.log(sum)
            setTotal(sum)


        } catch (error) {
            console.error(error)

        }
    }

    async function deleteCart(uuid: UUID) {
        try {
            await axios.delete(`http://localhost:8081/cart/${uuid}`)


        } catch (error) {
            console.error(error)

        }

    }

    async function postBuy(productId: number, quant: number, email: String, price: number) {
        const email_ = email

        try {
            console.log("entrou aqui", productId, price, email,)
            await axios.post(`http://localhost:8081/buy`, {
                productId: productId,
                nItens: quant,
                email: email_,
                price: price

            }).then(function (response: any) {
                console.log("adicionaou um buy")
                console.log(response);
            }).catch(function (error: any) {
                console.log(error)
            })

        } catch (error) {
            console.error(error)

        }

    }

    async function Load() {
        const debit = await newWallet()
        await findUser("marcos@gmail.com", debit)

    }


    useEffect(() => {
        Load()

    }, [])

    return (
        <>
            <header className={styles.h}>

                <h1 >Bem vindo ao seu carrinho {name}</h1>
                <h2>Saldo: {saldo}</h2>
                <h2>Total: {total}</h2>
                <div className={styles.products}>
                    {product.map((myCart: any) => (

                        <>
                            <div key={myCart.id} className={styles.item}>
                                <h2>{myCart.title}</h2>
                                <p>{myCart.description}</p>
                                <img src={myCart.image} alt={myCart.title} height={250} width={200} />
                                <p>Price: ${myCart.price}</p>
                                <p>Quantidade: {myCart.quantidade}</p>
                            </div>
                        </>
                    ))
                    }

                </div>
                <Link href="/purchase"><button onClick={() => deleteCart(myId)}>Finalizar Compra</button></Link>
            </header>
        </>
    )
}