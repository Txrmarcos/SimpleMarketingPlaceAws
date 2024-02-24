import Link from "next/link"
import styles from "../styles/Navbar.module.css";

export default function NavBar(): any {
    return (
        <>
            <div className={styles.container}>
                <ul className={styles.select}>
                    <li><Link href="/home">Home</Link></li>
                    <li><Link href="/cart">Carrinho</Link></li>
                    <li><Link href="/purchase">Compras</Link></li>
                </ul>
                <div className={styles.contText}>
                    <input className={styles.text} type="text" />
                </div>

            </div>
        </>
    )

}