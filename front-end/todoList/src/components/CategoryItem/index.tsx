import './style.css';

type Props = {
  color: string;
  title: string;
  quantity: number;
};

export default function CategoryItem({ color, title, quantity }: Props) {
  return (
    <div className="category-item">
      <div className="category-left">
        <span className="category-color" style={{ backgroundColor: color }} />

        <span>{title}</span>
      </div>

      <span>{quantity}</span>
    </div>
  );
}
