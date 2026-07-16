import './style.css';

type Props = {
  priority: string;
};

export default function TaskItem({ priority }: Props) {
  return (
    <div>
      <div>
        <span>{priority}</span>
      </div>
    </div>
  );
}
