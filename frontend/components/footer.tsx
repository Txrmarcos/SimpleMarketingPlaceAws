import style from "../styles/Footer.module.css";
import Link from "next/link";
export default function Footer() {
  return (
    <footer className={style.footer}>
      <div>
        <Link href="/">
          <>about</>
        </Link>
      </div>
      <div>
        <Link href="/">
          <>about</>
        </Link>
      </div>
      <div>
        <Link href="/">
          <>about</>
        </Link>
      </div>
    </footer>
  );
}
